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

/** Global variables */
String face_cascade_name = "haarcascade_frontalface_alt.xml";
String eyes_cascade_name = "haarcascade_eye_tree_eyeglasses.xml";
String mouth_cascade_name = "haarcascade_mcs_mouth.xml";
CascadeClassifier face_cascade;
CascadeClassifier eyes_cascade;
CascadeClassifier mouth_cascade;
string window_name = "Capture - Face detection";
RNG rng(12345);

/** @function main */
int main( int argc, const char** argv )
{
	CvCapture* capture;
	Mat frame;

//-- 1. Load the cascades
	if( !face_cascade.load( face_cascade_name ) ){ printf("--(!)Error loading\n"); return -1; };
	if( !eyes_cascade.load( eyes_cascade_name ) ){ printf("--(!)Error loading\n"); return -1; };
	if( !mouth_cascade.load( mouth_cascade_name)){ printf("--(!)Error loading\n"); return -1; };

//-- 2. Read the video stream
	capture = cvCaptureFromCAM( -1 );
	if( capture )
	{
		while( true )
		{
			frame = cvQueryFrame( capture );

//-- 3. Apply the classifier to the frame
			if( !frame.empty() )
			{ detectAndDisplay( frame ); }
			else
			{ printf(" --(!) No captured frame -- Break!"); break; }

			int c = waitKey(10);
			if( ((char)c == 'c' )||( (char)c == 'C' )) { break; }
			//else putText(frame, (char)c, Point(100,100), FONT_HERSHEY_PLAIN, 1.0, CV_RGB(0,0,255), 1.0);
		}
	} 
	return 0;
}

/** @function detectAndDisplay */
void detectAndDisplay( Mat frame )
{
	std::vector<Rect> faces;
	Mat frame_gray;

	cvtColor( frame, frame_gray, CV_BGR2GRAY );
	equalizeHist( frame_gray, frame_gray );

//-- Detect faces
	face_cascade.detectMultiScale( frame_gray, faces, 1.1, 2, 0|CV_HAAR_SCALE_IMAGE, Size(30, 30) );

	putText(frame, "Press C to exit application", Point(20,20), FONT_HERSHEY_PLAIN, 1.0, CV_RGB(0,255,0), 1.0);

	for( size_t i = 0; i < faces.size(); i++ )
	{
		Point center( faces[i].x + faces[i].width*0.5, faces[i].y + faces[i].height*0.5 );
		ellipse( frame, center, Size( faces[i].width*0.4, faces[i].height*0.5), 0, 0, 360, Scalar( 50, 255, 50 ), 4, 8, 0 );

		//ellipse( frame, center, Size( faces[i].width*0.8, faces[i].height*0.8), 0,0, 300, Scalar(  20, 100,  20),2,CV_AA,0);
		//ellipse( frame, center, Size( faces[i].width*0.7, faces[i].height*0.7), 0,0, 300, Scalar(  60, 150,  60),2,CV_AA,0);
		//ellipse( frame, center, Size( faces[i].width*0.6, faces[i].height*0.6), 0,0, 300, Scalar( 100, 200, 100),2,CV_AA,0);
		//ellipse( frame, center, Size( faces[i].width*0.5, faces[i].height*0.5), 0,0, 300, Scalar( 140, 255, 140),2,CV_AA,0);

		Mat faceROI = frame_gray( faces[i] );
		std::vector<Rect> eyes;

//-- In each face, detect eyes
		eyes_cascade.detectMultiScale( faceROI, eyes, 1.1, 2, 0 |CV_HAAR_SCALE_IMAGE, Size(30, 30) );

		for( size_t j = 0; j < eyes.size(); j++ )
		{
			Point center( faces[i].x + eyes[j].x + eyes[j].width*0.5, faces[i].y + eyes[j].y + eyes[j].height*0.5 );
			
			Point point1( faces[i].x + eyes[j].x + eyes[j].width*0.5, faces[i].y + eyes[j].y - eyes[j].height*0 );
			Point point2( faces[i].x + eyes[j].x + eyes[j].width*0.5, faces[i].y + eyes[j].y + eyes[j].height*1 );
			Point point3( faces[i].x + eyes[j].x + eyes[j].width*0, faces[i].y + eyes[j].y + eyes[j].height*0.5 );
			Point point4( faces[i].x + eyes[j].x + eyes[j].width*1, faces[i].y + eyes[j].y + eyes[j].height*0.5 );
			
			int radius = cvRound( (eyes[j].width + eyes[j].height)*0.2 );
			int radius2 = cvRound(eyes[j].width*0.2 );
			//circle( frame, center, radius, Scalar( 255, 255, 255 ), 40, CV_AA, 0 );
			circle( frame, center, radius2, Scalar( 0, 0, 255 ), 1, CV_AA, 0 );
			
			char text[255];
			sprintf(text, "Auge");
			putText(frame, text, point1, FONT_HERSHEY_DUPLEX, 0.5, CV_RGB( 0, 0, 255 ), 1.0);
			
			sprintf(text, "X: %d", (int)center.x);
			putText(frame, text, center, FONT_HERSHEY_DUPLEX, 0.5, CV_RGB( 0, 0, 255 ), 1.0);
			
			sprintf(text, "Y: %d",(int)center.y);
			putText(frame, text, point2, FONT_HERSHEY_DUPLEX, 0.5, CV_RGB( 0, 0, 255 ), 1.0);
			
			line(frame, point1, point2, Scalar( 0, 0, 255 ), 1, 8);
			line(frame, point3, point4, Scalar( 0, 0, 255 ), 1, 8);
		}
		
		std::vector<Rect> mouth;
		mouth_cascade.detectMultiScale( faceROI, mouth, 1.1, 2, 0 |CV_HAAR_SCALE_IMAGE, Size(30, 30) );
		
		for( size_t j = 0; j < mouth.size(); j++ )
		{
			Point center( faces[i].x + mouth[j].x + mouth[j].width*0.5, faces[i].y + mouth[j].y + mouth[j].height*0.5 );
			
			int radius = cvRound( (eyes[j].width + eyes[j].height)*0.2 );
			int radius2 = cvRound(eyes[j].width*0.2 );
			//circle( frame, center, radius, Scalar( 255, 255, 255 ), 40, CV_AA, 0 );
			circle( frame, center, radius2, Scalar( 0, 0, 255 ), 1, CV_AA, 0 );
			
		}
	}
//-- Show what you got
	imshow( window_name, frame );
}





