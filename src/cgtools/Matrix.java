/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

import static cgtools.Vector.*;

public final class Matrix {

  public static final Matrix identity;

  static {
    identity = new Matrix();
  }

  public static Matrix matrix(Direction b0, Direction b1, Direction b2) {
    Matrix m = new Matrix();
    m.set(0, 0, b0.x());
    m.set(1, 0, b0.y());
    m.set(2, 0, b0.z());
    m.set(0, 1, b1.x());
    m.set(1, 1, b1.y());
    m.set(2, 1, b1.z());
    m.set(0, 2, b2.x());
    m.set(1, 2, b2.y());
    m.set(2, 2, b2.z());
    return m;
  }

  public static Matrix matrix(Direction b0, Direction b1, Direction b2, Point b3) {
    Matrix m = new Matrix();
    m.set(0, 0, b0.x());
    m.set(1, 0, b0.y());
    m.set(2, 0, b0.z());
    m.set(0, 1, b1.x());
    m.set(1, 1, b1.y());
    m.set(2, 1, b1.z());
    m.set(0, 2, b2.x());
    m.set(1, 2, b2.y());
    m.set(2, 2, b2.z());
    m.set(0, 3, b3.x());
    m.set(1, 3, b3.y());
    m.set(2, 3, b3.z());
    return m;
  }

  public static Matrix identity() {
    return identity;
  }

  public static Matrix translation(Direction t) {
    Matrix m = new Matrix();
    m.set(3, 0, t.x());
    m.set(3, 1, t.y());
    m.set(3, 2, t.z());
    return m;
  }

  public static Matrix translation(Point t) {
    Matrix m = new Matrix();
    m.set(3, 0, t.x());
    m.set(3, 1, t.y());
    m.set(3, 2, t.z());
    return m;
  }

  public static Matrix translation(double x, double y, double z) {
    Matrix m = new Matrix();
    m.set(3, 0, x);
    m.set(3, 1, y);
    m.set(3, 2, z);
    return m;
  }

  public static Matrix rotation(Direction axis, double angle) {
    final Matrix m = new Matrix();
    final double rad = (angle / 180.0f) * ((double) Math.PI);
    final double cosa = (double) Math.cos(rad);
    final double sina = (double) Math.sin(rad);
    final double l = Math.sqrt(axis.x() * axis.x() + axis.y() * axis.y() + axis.z() * axis.z());
    final double rx = axis.x() / l;
    final double ry = axis.y() / l;
    final double rz = axis.z() / l;
    final double icosa = 1 - cosa;

    m.set(0, 0, (double) (icosa * rx * rx + cosa));
    m.set(0, 1, (double) (icosa * rx * ry + rz * sina));
    m.set(0, 2, (double) (icosa * rx * rz - ry * sina));

    m.set(1, 0, (double) (icosa * rx * ry - rz * sina));
    m.set(1, 1, (double) (icosa * ry * ry + cosa));
    m.set(1, 2, (double) (icosa * ry * rz + rx * sina));

    m.set(2, 0, (double) (icosa * rx * rz + ry * sina));
    m.set(2, 1, (double) (icosa * ry * rz - rx * sina));
    m.set(2, 2, (double) (icosa * rz * rz + cosa));
    return m;
  }

  public static Matrix rotation(double ax, double ay, double az, double angle) {
    return rotation(direction(ax, ay, az), angle);
  }

  public static Matrix scaling(double x, double y, double z) {
    Matrix m = new Matrix();
    m.set(0, 0, x);
    m.set(1, 1, y);
    m.set(2, 2, z);
    return m;
  }

  public static Matrix multiply(Matrix a, Matrix b, Matrix... ms) {
    Matrix r = a.multiply(b);
    for (Matrix m : ms)
      r = r.multiply(m);
    return r;
  }

  private Matrix multiply(Matrix m) {
    Matrix n = new Matrix();
    {
      {
        double v = 0;
        v += values[4 * 0 + 0] * m.values[4 * 0 + 0];
        v += values[4 * 1 + 0] * m.values[4 * 0 + 1];
        v += values[4 * 2 + 0] * m.values[4 * 0 + 2];
        v += values[4 * 3 + 0] * m.values[4 * 0 + 3];
        n.values[4 * 0 + 0] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 1] * m.values[4 * 0 + 0];
        v += values[4 * 1 + 1] * m.values[4 * 0 + 1];
        v += values[4 * 2 + 1] * m.values[4 * 0 + 2];
        v += values[4 * 3 + 1] * m.values[4 * 0 + 3];
        n.values[4 * 0 + 1] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 2] * m.values[4 * 0 + 0];
        v += values[4 * 1 + 2] * m.values[4 * 0 + 1];
        v += values[4 * 2 + 2] * m.values[4 * 0 + 2];
        v += values[4 * 3 + 2] * m.values[4 * 0 + 3];
        n.values[4 * 0 + 2] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 3] * m.values[4 * 0 + 0];
        v += values[4 * 1 + 3] * m.values[4 * 0 + 1];
        v += values[4 * 2 + 3] * m.values[4 * 0 + 2];
        v += values[4 * 3 + 3] * m.values[4 * 0 + 3];
        n.values[4 * 0 + 3] = v;
      }
    }
    {
      {
        double v = 0;
        v += values[4 * 0 + 0] * m.values[4 * 1 + 0];
        v += values[4 * 1 + 0] * m.values[4 * 1 + 1];
        v += values[4 * 2 + 0] * m.values[4 * 1 + 2];
        v += values[4 * 3 + 0] * m.values[4 * 1 + 3];
        n.values[4 * 1 + 0] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 1] * m.values[4 * 1 + 0];
        v += values[4 * 1 + 1] * m.values[4 * 1 + 1];
        v += values[4 * 2 + 1] * m.values[4 * 1 + 2];
        v += values[4 * 3 + 1] * m.values[4 * 1 + 3];
        n.values[4 * 1 + 1] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 2] * m.values[4 * 1 + 0];
        v += values[4 * 1 + 2] * m.values[4 * 1 + 1];
        v += values[4 * 2 + 2] * m.values[4 * 1 + 2];
        v += values[4 * 3 + 2] * m.values[4 * 1 + 3];
        n.values[4 * 1 + 2] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 3] * m.values[4 * 1 + 0];
        v += values[4 * 1 + 3] * m.values[4 * 1 + 1];
        v += values[4 * 2 + 3] * m.values[4 * 1 + 2];
        v += values[4 * 3 + 3] * m.values[4 * 1 + 3];
        n.values[4 * 1 + 3] = v;
      }
    }
    {
      {
        double v = 0;
        v += values[4 * 0 + 0] * m.values[4 * 2 + 0];
        v += values[4 * 1 + 0] * m.values[4 * 2 + 1];
        v += values[4 * 2 + 0] * m.values[4 * 2 + 2];
        v += values[4 * 3 + 0] * m.values[4 * 2 + 3];
        n.values[4 * 2 + 0] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 1] * m.values[4 * 2 + 0];
        v += values[4 * 1 + 1] * m.values[4 * 2 + 1];
        v += values[4 * 2 + 1] * m.values[4 * 2 + 2];
        v += values[4 * 3 + 1] * m.values[4 * 2 + 3];
        n.values[4 * 2 + 1] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 2] * m.values[4 * 2 + 0];
        v += values[4 * 1 + 2] * m.values[4 * 2 + 1];
        v += values[4 * 2 + 2] * m.values[4 * 2 + 2];
        v += values[4 * 3 + 2] * m.values[4 * 2 + 3];
        n.values[4 * 2 + 2] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 3] * m.values[4 * 2 + 0];
        v += values[4 * 1 + 3] * m.values[4 * 2 + 1];
        v += values[4 * 2 + 3] * m.values[4 * 2 + 2];
        v += values[4 * 3 + 3] * m.values[4 * 2 + 3];
        n.values[4 * 2 + 3] = v;
      }
    }
    {
      {
        double v = 0;
        v += values[4 * 0 + 0] * m.values[4 * 3 + 0];
        v += values[4 * 1 + 0] * m.values[4 * 3 + 1];
        v += values[4 * 2 + 0] * m.values[4 * 3 + 2];
        v += values[4 * 3 + 0] * m.values[4 * 3 + 3];
        n.values[4 * 3 + 0] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 1] * m.values[4 * 3 + 0];
        v += values[4 * 1 + 1] * m.values[4 * 3 + 1];
        v += values[4 * 2 + 1] * m.values[4 * 3 + 2];
        v += values[4 * 3 + 1] * m.values[4 * 3 + 3];
        n.values[4 * 3 + 1] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 2] * m.values[4 * 3 + 0];
        v += values[4 * 1 + 2] * m.values[4 * 3 + 1];
        v += values[4 * 2 + 2] * m.values[4 * 3 + 2];
        v += values[4 * 3 + 2] * m.values[4 * 3 + 3];
        n.values[4 * 3 + 2] = v;
      }
      {
        double v = 0;
        v += values[4 * 0 + 3] * m.values[4 * 3 + 0];
        v += values[4 * 1 + 3] * m.values[4 * 3 + 1];
        v += values[4 * 2 + 3] * m.values[4 * 3 + 2];
        v += values[4 * 3 + 3] * m.values[4 * 3 + 3];
        n.values[4 * 3 + 3] = v;
      }
    }
    return n;
  }

  public static Point multiply(Matrix m, Point p) {
    final double x = m.get(0, 0) * p.x() + m.get(1, 0) * p.y() + m.get(2, 0) * p.z() + m.get(3, 0);
    final double y = m.get(0, 1) * p.x() + m.get(1, 1) * p.y() + m.get(2, 1) * p.z() + m.get(3, 1);
    final double z = m.get(0, 2) * p.x() + m.get(1, 2) * p.y() + m.get(2, 2) * p.z() + m.get(3, 2);
    return point(x, y, z);
  }

  public static Direction multiply(Matrix m, Direction d) {
    double x = m.get(0, 0) * d.x() + m.get(1, 0) * d.y() + m.get(2, 0) * d.z();
    double y = m.get(0, 1) * d.x() + m.get(1, 1) * d.y() + m.get(2, 1) * d.z();
    double z = m.get(0, 2) * d.x() + m.get(1, 2) * d.y() + m.get(2, 2) * d.z();
    return direction(x, y, z);
  }

  public static Matrix transpose(Matrix m) {
    Matrix n = new Matrix();
    for (int c = 0; c != 4; c++) {
      for (int r = 0; r != 4; r++) {
        n.set(c, r, m.get(r, c));
      }
    }
    return n;
  }

  public static Matrix invert(Matrix m) {
    Matrix ret = new Matrix();
    double[] mat = m.values;
    double[] dst = ret.values;
    double[] tmp = new double[12];

    /* temparray for pairs */
    double src[] = new double[16];

    /* array of transpose source matrix */
    double det;

    /* determinant */
    /*
     * transpose matrix
     */
    for (int i = 0; i < 4; i++) {
      src[i] = mat[i * 4];
      src[i + 4] = mat[i * 4 + 1];
      src[i + 8] = mat[i * 4 + 2];
      src[i + 12] = mat[i * 4 + 3];
    }

    /* calculate pairs for first 8 elements (cofactors) */
    tmp[0] = src[10] * src[15];
    tmp[1] = src[11] * src[14];
    tmp[2] = src[9] * src[15];
    tmp[3] = src[11] * src[13];
    tmp[4] = src[9] * src[14];
    tmp[5] = src[10] * src[13];
    tmp[6] = src[8] * src[15];
    tmp[7] = src[11] * src[12];
    tmp[8] = src[8] * src[14];
    tmp[9] = src[10] * src[12];
    tmp[10] = src[8] * src[13];
    tmp[11] = src[9] * src[12];

    /* calculate first 8 elements (cofactors) */
    dst[0] = tmp[0] * src[5] + tmp[3] * src[6] + tmp[4] * src[7];
    dst[0] -= tmp[1] * src[5] + tmp[2] * src[6] + tmp[5] * src[7];
    dst[1] = tmp[1] * src[4] + tmp[6] * src[6] + tmp[9] * src[7];
    dst[1] -= tmp[0] * src[4] + tmp[7] * src[6] + tmp[8] * src[7];
    dst[2] = tmp[2] * src[4] + tmp[7] * src[5] + tmp[10] * src[7];
    dst[2] -= tmp[3] * src[4] + tmp[6] * src[5] + tmp[11] * src[7];
    dst[3] = tmp[5] * src[4] + tmp[8] * src[5] + tmp[11] * src[6];
    dst[3] -= tmp[4] * src[4] + tmp[9] * src[5] + tmp[10] * src[6];
    dst[4] = tmp[1] * src[1] + tmp[2] * src[2] + tmp[5] * src[3];
    dst[4] -= tmp[0] * src[1] + tmp[3] * src[2] + tmp[4] * src[3];
    dst[5] = tmp[0] * src[0] + tmp[7] * src[2] + tmp[8] * src[3];
    dst[5] -= tmp[1] * src[0] + tmp[6] * src[2] + tmp[9] * src[3];
    dst[6] = tmp[3] * src[0] + tmp[6] * src[1] + tmp[11] * src[3];
    dst[6] -= tmp[2] * src[0] + tmp[7] * src[1] + tmp[10] * src[3];
    dst[7] = tmp[4] * src[0] + tmp[9] * src[1] + tmp[10] * src[2];
    dst[7] -= tmp[5] * src[0] + tmp[8] * src[1] + tmp[11] * src[2];

    /* calculate pairs for second 8 elements (cofactors) */
    tmp[0] = src[2] * src[7];
    tmp[1] = src[3] * src[6];
    tmp[2] = src[1] * src[7];
    tmp[3] = src[3] * src[5];
    tmp[4] = src[1] * src[6];
    tmp[5] = src[2] * src[5];
    tmp[6] = src[0] * src[7];
    tmp[7] = src[3] * src[4];
    tmp[8] = src[0] * src[6];
    tmp[9] = src[2] * src[4];
    tmp[10] = src[0] * src[5];
    tmp[11] = src[1] * src[4];

    /* calculate second 8 elements (cofactors) */
    dst[8] = tmp[0] * src[13] + tmp[3] * src[14] + tmp[4] * src[15];
    dst[8] -= tmp[1] * src[13] + tmp[2] * src[14] + tmp[5] * src[15];
    dst[9] = tmp[1] * src[12] + tmp[6] * src[14] + tmp[9] * src[15];
    dst[9] -= tmp[0] * src[12] + tmp[7] * src[14] + tmp[8] * src[15];
    dst[10] = tmp[2] * src[12] + tmp[7] * src[13] + tmp[10] * src[15];
    dst[10] -= tmp[3] * src[12] + tmp[6] * src[13] + tmp[11] * src[15];
    dst[11] = tmp[5] * src[12] + tmp[8] * src[13] + tmp[11] * src[14];
    dst[11] -= tmp[4] * src[12] + tmp[9] * src[13] + tmp[10] * src[14];
    dst[12] = tmp[2] * src[10] + tmp[5] * src[11] + tmp[1] * src[9];
    dst[12] -= tmp[4] * src[11] + tmp[0] * src[9] + tmp[3] * src[10];
    dst[13] = tmp[8] * src[11] + tmp[0] * src[8] + tmp[7] * src[10];
    dst[13] -= tmp[6] * src[10] + tmp[9] * src[11] + tmp[1] * src[8];
    dst[14] = tmp[6] * src[9] + tmp[11] * src[11] + tmp[3] * src[8];
    dst[14] -= tmp[10] * src[11] + tmp[2] * src[8] + tmp[7] * src[9];
    dst[15] = tmp[10] * src[10] + tmp[4] * src[8] + tmp[9] * src[9];
    dst[15] -= tmp[8] * src[9] + tmp[11] * src[10] + tmp[5] * src[8];

    /* calculate determinant */
    det = src[0] * dst[0] + src[1] * dst[1] + src[2] * dst[2] + src[3] * dst[3];

    if (det == 0.0f) {
      throw new RuntimeException("singular matrix is not invertible");
    }

    /* calculate matrix inverse */
    det = 1 / det;

    for (int j = 0; j < 16; j++) {
      dst[j] *= det;
    }

    return ret;
  }

  @Override
  public String toString() {
    String s = "";
    for (int r = 0; r < 4; r++) {
      s += "( ";
      for (int c = 0; c < 4; c++) {
        s += get(c, r) + " ";
      }
      s += ")\n";
    }
    return s;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Matrix))
      return false;
    if (o == this)
      return true;
    Matrix m = (Matrix) o;
    for (int i = 0; i != 16; i++)
      if (values[i] != m.values[i])
        return false;
    return true;
  }

  public boolean equals(Matrix m, double epsilon) {
    for (int i = 0; i != 16; i++)
      if (!Util.areEqual(values[i], m.values[i]))
        return false;
    return true;
  }

  private Matrix() {
    makeIdentity();
  }

  private double get(int c, int r) {
    return values[4 * c + r];
  }

  private void set(int c, int r, double v) {
    values[4 * c + r] = v;
  }

  private Matrix makeIdentity() {
    values = new double[16];
    set(0, 0, 1.0f);
    set(1, 1, 1.0f);
    set(2, 2, 1.0f);
    set(3, 3, 1.0f);
    return this;
  }

  private double[] values;
}
