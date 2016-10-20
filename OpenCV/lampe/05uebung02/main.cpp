#include "opencv2/opencv.hpp"
#include <string>
using namespace cv;
using namespace std;

void showImage(){
	const string path = "kitten.jpg";
	Mat image = imread(path, CV_LOAD_IMAGE_COLOR);
	//			breite				  				hoehe
	cout << "Size: " << image.size().width << " x " << image.size().height << endl;
	namedWindow( "Display window", CV_WINDOW_AUTOSIZE );
	imshow( "Display window", image ); 

	waitKey(0);
}

int main(){
	showImage();
	return 0;
}
