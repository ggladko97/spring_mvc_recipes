package com.hladkevych.menu.utils.mlp;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 * Created by ggladko97 on 23.01.18.
 */
public class MlpConfiguration {
    
  private static final Logger logger = Logger.getLogger(MlpConfiguration.class);
  private DataSet allData;
  private DataSet trainData;
  private DataSet testData;

  private int numInputs = 51;
  private int outputNum = 13;
  private int iterations = 1000;
  private int seed = 8;

  public MlpConfiguration(int numInputs, int outputNum, int iterations, int seed) {
    this.numInputs = numInputs;
    this.outputNum = outputNum;
    this.iterations = iterations;
    this.seed = seed;
    try {
      allData = readDataFromCsv("prod_beta_numeric.csv");
      allData.shuffle();
      SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.85);
      trainData = testAndTrain.getTrain();
      testData = testAndTrain.getTest();
      logger.info("Train: " + trainData);
      logger.info("Test: " + testData);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  private MultiLayerConfiguration createNetwork() {
    MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
        .seed(seed)
        .iterations(iterations)
        .activation(Activation.TANH)
        .weightInit(WeightInit.XAVIER)
        .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
        .learningRate(0.1)
        .regularization(true).l2(1e-4)
        .list()
        .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(outputNum).build())
        .layer(1, new DenseLayer.Builder().nIn(outputNum).nOut(outputNum).build())
        .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
            .activation(Activation.SOFTMAX)
            .nIn(outputNum).nOut(outputNum)
            .build()).backprop(true)
        .pretrain(false)
        .build();
    return conf;
  }

  private MultiLayerNetwork trainNetwork() {
    MultiLayerNetwork model = new MultiLayerNetwork(createNetwork());
    model.init();
    model.fit(trainData);
    return model;
  }

  public INDArray validateNetwork() {
    MultiLayerNetwork multiLayerNetwork = trainNetwork();
    return multiLayerNetwork.output(testData.getFeatureMatrix());
  }

  public INDArray classifyRow(DataSet row) {
    MultiLayerNetwork multiLayerNetwork = trainNetwork();
    return multiLayerNetwork.output(row.getFeatureMatrix());
  }

  public DataSet readDataFromCsv(String path) throws IOException, InterruptedException {
    RecordReader recordReader = new CSVRecordReader(0, ',');
    DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, 70, 0, 13);

    recordReader.initialize(new FileSplit(new ClassPathResource(path).getFile()));
    return iterator.next();
  }
}
