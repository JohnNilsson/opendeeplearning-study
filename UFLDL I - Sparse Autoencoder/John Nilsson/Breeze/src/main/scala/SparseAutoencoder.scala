package nu.milsson.ufldl.tutorial.sparseautoencoder

import breeze.optimize.LBFGS
import java.io.File
import com.jmatio.io.MatFileReader
import com.jmatio.types.MLDouble
import scala.util.Random
import breeze.linalg.DenseMatrix
import breeze.plot._

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

  def sampleIMAGES() = {
    val patchsize = 8
    val numpatches = 10000

    val img = getRandomImage()

    val fig = Figure()
    fig.subplot(0) += image(img)
  }
}

object SparseAutoencoder extends App {
  IMAGES.sampleIMAGES()
}