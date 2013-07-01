name := "UFLDL Tutorial: Sparse Autoencoder in Breeze"

version := "0.1"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
	"net.sourceforge.jmatio" % "jmatio" % "1.0",
	"org.scalanlp" %% "breeze-math" % "0.4-SNAPSHOT",
	"org.scalanlp" %% "breeze-viz" % "0.4-SNAPSHOT"
//	"org.scalanlp" %% "breeze-learn" % "0.4-SNAPSHOT",
//	"org.scalanlp" %% "breeze-process" % "0.4-SNAPSHOT",
//	"org.scalanlp" % "nak" % "1.1.3"
)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// initialCommands in console := "import _root_.nu.milsson.ufldl.tutorial.sparseautoencoder._"