#include "opencv2/opencv.hpp"
#include <iostream>
#include <string> 
using namespace cv;
using namespace std;
int main(){
	Mat image;
	image = imread("kitten.jpg", CV_LOAD_IMAGE_COLOR);
	namedWindow( "Display window", CV_WINDOW_AUTOSIZE );// Create a window for display.
    imshow( "Display window", image );                   // Show our image inside it.

    waitKey(0);                                          // Wait for a keystroke in the window
    return 0;
}