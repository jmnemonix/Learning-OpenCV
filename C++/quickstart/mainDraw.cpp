#include "opencv2/objdetect/objdetect.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"

#include <iostream>
#include <stdio.h>

/*

	http://docs.opencv.org/doc/tutorials/objdetect/cascade_classifier/cascade_classifier.html

*/

using namespace std;
using namespace cv;

/** Function Headers */
void detectAndDisplay( Mat frame );
    
  string window_name = "Capture - Draw";
  RNG rng(12345);

  /** @function main */
  int main( int argc, const char** argv ){
    
	  CvCapture* capture;
	  Mat frame;

	  //-- 2. Read the video stream
	  capture = cvCaptureFromCAM( -1 );
	  if( capture ){
	    
		  while( true ){
		    
			  frame = cvQueryFrame( capture );

			  //-- 3. Apply the classifier to the frame
			  if( !frame.empty() )
			  { detectAndDisplay( frame ); }
			  else
			  { printf(" --(!) No captured frame -- Break!"); break; }

			  int c = waitKey(10);
			  if( ((char)c == 'c' )||( (char)c == 'C' )) { break; }
		  }
	  } 
	  return 0;
  }

  /** @function detectAndDisplay */
      void detectAndDisplay( Mat frame ){
    
	  Mat frame_gray;
	  cvtColor( frame, frame_gray, CV_BGR2GRAY );
	  equalizeHist( frame_gray, frame_gray );

	  Point center( 50, 50);		
	  int radius = cvRound(50);
	  
	  putText(frame, "Press C to exit application", Point(20,20), FONT_HERSHEY_PLAIN, 1.0, CV_RGB(0,255,0), 1.0);
	  circle( frame, center, radius, Scalar( 0, 0, 255 ), 1, CV_AA, 0 );
		  
		  
	
	imshow( window_name, frame );
}




