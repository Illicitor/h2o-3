package hex.genmodel.algos.glrm;

import hex.genmodel.ModelMojoReader;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 */
public class GlrmMojoReader extends ModelMojoReader<GlrmModel> {

  @Override
  protected void readModelData() throws IOException {
    _model._regx = GlrmRegularizer.valueOf((String) readkv("regularizationX"));
    _model._gammax = readkv("gammaX");
    _model._ncolY = readkv("ncolY");
    _model._nrowY = readkv("nrowY");

    // archetypes
    _model._archetypes = new double[_model._nrowY][];
    ByteBuffer bb = ByteBuffer.wrap(readblob("archetypes"));
    for (int i = 0; i < _model._nrowY; i++) {
      double[] row = new double[_model._ncolY];
      _model._archetypes[i] = row;
      for (int j = 0; j < _model._ncolY; j++)
        row[j] = bb.getDouble();
    }
  }

  @Override
  protected GlrmModel makeModel(String[] columns, String[][] domains) {
    return new GlrmModel(columns, domains);
  }

}