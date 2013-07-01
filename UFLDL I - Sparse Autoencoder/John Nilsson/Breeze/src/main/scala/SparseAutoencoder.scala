package nu.milsson.ufldl.tutorial.sparseautoencoder

import breeze.optimize.LBFGS
import java.io.File
import com.jmatio.io.MatFileReader
import com.jmatio.types.MLDouble
import scala.util.Random
import breeze.linalg._
import breeze.numerics._
import breeze.plot._
import breeze.storage._
import scala.reflect._
import breeze.util.Implicits._

object IMAGES {

  /**
   * Load IMAGES.mat using jmatio.
   */
  def load() = new MatFileReader("IMAGES.mat")
    .getMLArray("IMAGES")
    // jmatio provides some matlab like types to represent matrixes
    // and vecotors
    .asInstanceOf[MLDouble]
    // but we just want the underlying data to pass on
    // to breeze. getRealByteBuffer() provides direct access to the
    // memory mapped data.
    .getRealByteBuffer()
    .asDoubleBuffer()

  /**
   * Get one of the images in IMAGES.mat
   */
  def getImage(img: Int) = {
    val size = 512 * 512

    // Breezs DenseMatrix wants an Array as backing source.
    // The DoubleBuffer.get should provide the quickest way to copy
    // the data into an array.
    val array = new Array[Double](size)
    var images = load()
    images.position(img * size)
    images.get(array, 0, size)

    DenseMatrix.create(512, 512, array)
  }

  def getRandomImage() = getImage(Random.nextInt(10))

  /**
   * Flip matrix upside down
   */
  def flipud[@specialized(Int, Float, Double) D](m: DenseMatrix[D])(implicit ev1: ClassTag[D], ev2: DefaultArrayValue[D]): DenseMatrix[D] =
    DenseMatrix.tabulate(m.rows, m.cols) {
      (r, c) => m(m.rows - r - 1, c)
    }

  def displayImage(img: DenseMatrix[Double]) {
    val fig = Figure()
    fig.height = img.rows + 16
    fig.width = img.cols + 16
    // Since Breeze insists on rendering from lower left corner
    // flip the matrix around to acommodate
    fig.subplot(0) += image(flipud(img), GradientPaintScale(img.min, img.max, PaintScale.BlackToWhite))
  }

  def sampleIMAGES() = {
    val patchsize = 8
    val numpatches = 10000

    val img = getRandomImage()

    displayImage(img)
  }
}

object SparseAutoencoder extends App {
  IMAGES.sampleIMAGES()
}