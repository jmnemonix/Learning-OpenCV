#include "opencv2/opencv.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <string>
using namespace cv;
using namespace std;

void showVideo(){
	const string path = "Micro.avi";
	// klasse zum laden von videos oder webcams
	VideoCapture vcapture;
	// oeffnen der source 
	// mit 0 laden wir die webcam
	vcapture.open(0);
	// trackbar
	createTrackbar( "trackbar"Linear Blend", 0, 255);
	// neues fenster erstellen
	namedWindow( "video", CV_WINDOW_AUTOSIZE );
	// schleife fuer das durchlaufen des videos
	while(true){
		// wir erstellen uns eine frame variable
		Mat videoFrame;
		// Videoframe lesen
		bool success = vcapture.read(videoFrame);
		// wenn was schief laeuft
		if (success == false){
			break;
		}
		// videoframe anzeigen im fenster
		imshow("video", videoFrame);
		// warten auf button press mit 30 ms delay
		int key = waitKey(30);

		if (key != -1)	
		{
			break;
		}
	}
}

int main(){
	showVideo();
	return 0;
}