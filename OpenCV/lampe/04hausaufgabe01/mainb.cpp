#include "opencv2/opencv.hpp"
#include <iostream>
using namespace cv;
int main(){
	Mat image(480, 640, CV_8UC3);
	int radius = rand() % 50;
	int i = 0;
	for (i=0; i<1000; i++)
	{	
		Point center(rand() % 680,rand() % 640);
		circle(image, center, radius, Scalar( rand() % 255, rand() % 255, 255),CV_FILLED);
	}
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}