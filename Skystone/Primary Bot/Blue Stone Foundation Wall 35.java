package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

@Autonomous(name="Blue Stone Foundation Wall 35", group="Auto")
public class Blue_Stone_Foundation_Wall_35 extends LinearOpMode {

 ////////////////////////////////////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////| 
 ////////////////////////////////////////|
 /////////  MEG'S TEST PROGRAM  /////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////|
 ////////////////////////////////////////| 
 
/*
This program was made for testing purposes to see if it is possible to grab both sky stones 
the foundation and park in 30 seconds.
As of 1/30/20 I have not increased the speed of any of the movements, so it will not be possible
to complete these tasks until I do that. I have and will take from both Blue_Stone_Bridge_Cedar 
and Blue_Foundation_Wall_Cedar. I made this program as a copy of Blue_Stone_Bridge_Cedar and
have not yet added in the Foundation movments yet.
*/ 
 
 
     /* Declare OpMode members. */
     RobotHardware robot = new RobotHardware();
     private ElapsedTime runtime = new ElapsedTime();
     
    ColorSensor sensorColor0;
    ColorSensor sensorColor1;

  
 
     @Override
     public void runOpMode() throws InterruptedException{
      
      try  {
      
      
     /*
     * Initialize the drive system variables.
     * The init() method of the hardware class does all the work here
     */
     robot.init(hardwareMap);
     
       // get a reference to the color sensor.
        sensorColor0 = hardwareMap.get(ColorSensor.class, "sensorColor0");
        sensorColor1 = hardwareMap.get(ColorSensor.class, "sensorColor1");
        // get a reference to the distance sensor that shares the same name.
       // sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues0[] = {0F, 0F, 0F};
        float hsvValues1[] = {0F, 0F, 0F};
        // values is a reference to the hsvValues array.
       

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        char skystoneLocation = 'r';
        boolean position = false;


        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        telemetry.addData("Mode", "calibrating2...");
        telemetry.update();

        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            Thread.sleep(50);
            idle();
        }


     
     // Wait for the game to start (driver presses PLAY)
        /** Wait for the game to begin **/
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        waitForStart();
        
 
            if (opModeIsActive()) {
             
             
             ///////////////////////////////////////////////////////////////////////////////////////////////////////
             /////////////////////////// Get to Block here/////////////////////////////////////////////////////////
             /////////////////////////////////////////////////////////////////////////////////////////////////////
             
                    
                    //Move away from depot
                    robot.backwardByEncoder(0.25 , -300); // was -700
                    Thread.sleep(50);
                    
                    //Move to blocks
                    robot.leftByEncoder(0.9 , -6700);
                    robot.leftByEncoder(0.2 , -1874);
                    Thread.sleep(50);
                    
                    //Scan
                    while (position != true){
            Color.RGBToHSV((int) (sensorColor0.red() * SCALE_FACTOR),
                    (int) (sensorColor0.green() * SCALE_FACTOR),
                    (int) (sensorColor0.blue() * SCALE_FACTOR),
                    hsvValues0);
            Color.RGBToHSV((int) (sensorColor1.red() * SCALE_FACTOR),
                    (int) (sensorColor1.green() * SCALE_FACTOR),
                    (int) (sensorColor1.blue() * SCALE_FACTOR),
                    hsvValues1);
                    
                    //Telemtry Return
                    telemetry.addData("Hue for color sensor 0", hsvValues0[0]);
                    telemetry.addData("----First color Sensor ends here" , "----");
                    telemetry.addData("Hue for sensor 1", hsvValues1[0]);
                    telemetry.addData("----Second color Sensor ends here" , "----");
                    telemetry.update();
                    
                    Thread.sleep(100);
                    
                    if (hsvValues1[0] > 105 && hsvValues0[0] < 105){
                
                /////////////////////////////////////////////////////////////////////////////
                ////////////////// This Is Middle     5     2   !!!!!! ///////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////
                    
                    //Move closer to stone
                    robot.leftByEncoder(0.3, -600); //Was 0.2  | change to 0.6
                    Thread.sleep(50);
                    
                    //Grab stone
                    robot.blockBlue.setPosition(0.1); 
                    Thread.sleep(750);
                    
                    //Slightly rotate away from stone
                    robot.rotate(2, 0.2); // was 6 
                    Thread.sleep(100);
                    
                    //Move away from stone 
                    robot.rightByEncoder(0.8, 2100); // was 0.5 | change to 0.6
                    robot.rightByEncoder(0.6, 300); // was 0.2 | change to 0.6
                    Thread.sleep(50);
                    
                    //Go under bridge
                    robot.backwardByEncoder(0.95 , -15688); // was 0.3
                    Thread.sleep(50);
                    
                    //Release Stone
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    //Move back to stones
                    robot.forwardByEncoder(0.95 , 11000); // was 0.3
                    robot.forwardByEncoder(0.8 , 10808); // was 0.3
                    Thread.sleep(50);
                    
                    //Slam against wall
                    robot.backward(0.2); //was forward
                    Thread.sleep(750);
                    
                    //Move to stones
                    robot.leftByEncoder(0.7 , -3475); // was 0.2
                    Thread.sleep(50);
                    
                    //Harder against wall
                    robot.backward(); // was forward
                    Thread.sleep(100);
                    robot.stop();
                    
                    //Grab block
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    //Move away from Stones
                    robot.rightByEncoder(0.75 , 3000); // was 0.3
                    Thread.sleep(50);
                    
                    //Move towards bridge
                    robot.backwardByEncoder(0.85 , -11000); // was 0.3
                    Thread.sleep(50);
                    
                    //Slide away from bridge to avoid collision
                    robot.rightByEncoder(0.6 , 900); // was 0.3 
                    Thread.sleep(50);
                    
                    //Rotate slightly to avoid collision with bridge
                    robot.rotate(2, 0.5);
                    Thread.sleep(50);
                    
                    //Continue under bridge
                    robot.backwardByEncoder(0.8 , -11808); // was 0.3
                    Thread.sleep(50);
                    
                    ///////////////////////////////////////////////////////////|||
                    ///////////////////////////////////////////////////////////|||
                    ///////////////////////////////////////////////////////////|||
                    ////EVERYTHING AFTER THIS POINT HAS BEEN CHANGED OR ADDED//||| nice
                    ///////////////////////////////////////////////////////////|||
                    ///////////////////////////////////////////////////////////|||
                    ///////////////////////////////////////////////////////////|||
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    //Move backward to foundation
                    robot.backwardByEncoder(0.75 , 2160); // was 3400 
                    Thread.sleep(50);
                    
                    //Continue moving to foundation
                    robot.backwardByEncoder(0.4, 1500); // was 1200
                    Thread.sleep(50);
                    
                    //Rotate to foundation 
                    robot.rotate(-90, 0.4);
                    Thread.sleep(50);     
                    
                    //Move forward to foundation
                    robot.backwardByEncoder(0.75, -2500); // was 0.3
                    Thread.sleep(50);
                    
                    /////////////////////////////////
                    /*
                    After this point the code is taken from Blue Foundation Wall
                    Release block at some point
                    */
                    ///////////////////////////////// ok
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(750);
                     
                    // Pull Foundation Away Approx. 18in
                    robot.forwardByEncoder(0.75, 5800); 
                    
                    // Turn Foundation
                    robot.resetAngle();
                    robot.rotate(82, 0.5); // was -
                    Thread.sleep(100);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);
                    robot.hook1.setPosition(0.25);
                    Thread.sleep(1000);  
                    
                    // Release stone
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    // Move to Wall
                    robot.right();
                    Thread.sleep(1000);
                    robot.stop();
                    
                    // Move  to beneath skybridge
                    robot.forwardByEncoder(0.8 , 13000);
                    Thread.sleep(100); 
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                    
                    
                    robot.stop();
                    
                    
                    Thread.sleep(3000);
                    
            }   else if (hsvValues0[0] > 105 && hsvValues1[0] < 105){ 

                    //////////////////////////////////////////////////////////////////
                    ///////////// skystone is      1     4  /////////////////////////
                    ////////////////////////////////////////////////////////////////
                    
                    
                    // robot.forwardByEncoder(0.2 , 400);
                    // Thread.sleep(50);
                    
                    //Move closer to stones
                    robot.leftByEncoder(0.25, -600);
                    Thread.sleep(50);
                    
                    // robot.backwardByEncoder(0.3 , -150);
                    // Thread.sleep(50);
                    
                    //Grab stone
                     robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    //Move away from stones
                    robot.rightByEncoder(0.75, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    //Rotate slightly away from stones
                    robot.rotate(1, 0.2);
                    Thread.sleep(100);
                    
                    //Move under bridge
                    robot.backwardByEncoder(0.75 , -15988); // was 0.3
                    Thread.sleep(50);
                    
                    //Release stone
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);
                    
                    //Move back to stones
                    robot.forwardByEncoder(0.9 , 11300);
                    robot.forwardByEncoder(0.8 , 11108);
                    Thread.sleep(50);
                    
                    //Slam into wall
                    robot.backward(0.2); // was forward
                    Thread.sleep(750);
                    
                    //Move to blocks
                    robot.leftByEncoder(0.5 , -3475);
                    Thread.sleep(50);
                    
                    //Slam into walls harder
                    robot.backward(); // was forward
                    Thread.sleep(100);
                    robot.stop();
                    
                    //Grab stone
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    //Rotate slightly away from stones
                    robot.rotate(2, 0.3);
                    
                    //Slide away from stones
                    robot.rightByEncoder(0.4 , 3500); // was 3400
                    Thread.sleep(50);
                    
                    //Go back under bridge
                    robot.backwardByEncoder(0.9 , -15300); // was 0.3
                    robot.backwardByEncoder(0.75 , -8600); // was 0.3
                    Thread.sleep(50);
                    // Need to increase these values
                    
                    /////////////////////////////////
                    /*
                    After this point the code is taken from Blue Foundation Wall
                    Release block at some point
                    */
                    /////////////////////////////////
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(1000);
                     
                    // Pull Foundation Away Approx. 18in
                    robot.forwardByEncoder(0.75, 5800);
                    
                    // Turn Foundation
                    robot.resetAngle();
                    robot.rotate(82, 0.5); // was -
                    Thread.sleep(100);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);
                    robot.hook1.setPosition(0.25);
                    Thread.sleep(750);  
                    
                    // Release stone
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    // Move to Wall
                    robot.right();
                    Thread.sleep(1000);
                    robot.stop();
                    
                    // Move  to beneath skybridge
                    robot.forwardByEncoder(0.8 , 13000);
                    Thread.sleep(100); 
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                        
                        robot.stop();
                        
                     Thread.sleep(30000);

                    
            }   else {
                    
                    //////////////////////////////////////////////////////////////////////////////////////////////
                    ////////////  Skystone position 3  6 /////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////
                    
                    //Move up to stones
                    robot.forwardByEncoder(0.5, 1800);
                    Thread.sleep(50);
                    
                    //Down to stones
                    robot.leftByEncoder(0.25, -600);
                    Thread.sleep(50);
                    
                    //Grab stone
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    //Rotate slightly away from stones
                    robot.rotate(1, 0.2);
                    Thread.sleep(100);
                    
                    //Shift away from stones
                    robot.rightByEncoder(0.75, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    //Back up under bridge
                    robot.backwardByEncoder(0.9 , -17000); // was 17000
                    Thread.sleep(50);
                    
                    //Release stone
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);
                    
                    //Back under bridge for stone
                    robot.forwardByEncoder(0.75 , 9500); // was 9800
                    Thread.sleep(50);
                    
                    //Push against stone
                    robot.leftByEncoder(0.3 , -2568);
                    Thread.sleep(50);
                    
                    //Slowly push against stone
                    robot.right(0.2); // was left
                    Thread.sleep(400);
                    robot.stop();
                    
                    //Grab stone
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    //Move away from stones
                    robot.rightByEncoder(0.6 , 3100);
                    Thread.sleep(50);
                    
                    //Backup under bridge
                    robot.backwardByEncoder(0.75 , -11300);
                    Thread.sleep(50);
                    
                    //Need to change these values
                    
                    /*
                    /////////////////////////////////
                    /////////////////////////////////
                    /////////////////////////////////
                    
                    After this point the code is taken from Blue Foundation Wall
                    Release block at some point
                   
                    /////////////////////////////////
                    /////////////////////////////////
                    /////////////////////////////////
                    */
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(750);
                     
                    // Pull Foundation Away Approx. 18in
                    robot.forwardByEncoder(0.75, 5800);
                    
                    // Turn Foundation
                    robot.resetAngle();
                    robot.rotate(82, 0.5); // was -
                    Thread.sleep(100);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);
                    robot.hook1.setPosition(0.25);
                    Thread.sleep(750);  
                    
                    // Release stone
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    // Move to Wall
                    robot.right();
                    Thread.sleep(1000);
                    robot.stop();
                    
                    // Move  to beneath skybridge
                    robot.forwardByEncoder(0.8 , 13000);
                    Thread.sleep(100); 
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                    
                        robot.stop();
                        
                     Thread.sleep(30000);
                        
                  
            }
            
            Thread.sleep(500);
            


            telemetry.update();
            

            }
         }
         
       
      } catch(InterruptedException e){
 telemetry.addLine("Hey Caught Interruption");
 telemetry.update();
 robot.stop();
//  robot.ssExtend.setPower(0);
//  robot.ssScrew.setPower(0);
 Thread.sleep(500);
}
       
     }
  }
