#include "opencv2/opencv.hpp"
#include <iostream>
using namespace cv;
int main(){
	Mat image(640, 640, CV_8UC3);
	Scalar color2 (255, 255, 255);
	int x = 0;
	int y = 64;
	int x2 = 0;
	int y2 = 64;
	for (int i=0; i<10; i++){
	//ungerade
		if (i % 2) {
			y = 128;
		    x = 64;
		}else{
			y = 64;
			x = 0;
		}
		for (int j=0; j<10; j++){
			Point p1 = Point (x,x2);
			Point p2 = Point (y,y2);
			rectangle(image, p1 , p2, color2,CV_FILLED);
			y += 128;
			x += 128;
		}
		y2 += 64;
		x2 += 64;
	}
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}