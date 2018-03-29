package com.hladkevych.menu.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/**
 * Created by ggladko97 on 14.05.17.
 */
public class TxtUtils {

  public static String stringFromInputStream(InputStream inputStream) throws IOException {
    return IOUtils.toString(inputStream,"UTF-8");
  }
}
