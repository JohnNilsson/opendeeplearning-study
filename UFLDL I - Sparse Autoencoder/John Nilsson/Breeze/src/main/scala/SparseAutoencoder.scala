package nu.milsson.ufldl.tutorial.sparseautoencoder

import breeze.optimize.LBFGS
import java.io.File
import com.jmatio.io.MatFileReader
import com.jmatio.types.MLDouble
import scala.util.Random
import breeze.linalg.DenseMatrix
import breeze.plot._

object IMAGES {

  def load() = new MatFileReader("IMAGES.mat")
    .getMLArray("IMAGES")
    .asInstanceOf[MLDouble]
    .getRealByteBuffer()
    .asDoubleBuffer()

  def getImage(img: Int) = {
    val size = 512 * 512
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