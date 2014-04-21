#include "opencv2/opencv.hpp"
#include <iostream>
using namespace cv;
int main(){
	Mat image(480, 640, CV_8UC3);
	int radius = 40;
	int i = 0;
	for (i=0; i<10; i++)
	{	
		Point center(320,240);
		circle(image, center, radius, Scalar( 55, 55, 55));
		radius = radius + 20;
	}
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}