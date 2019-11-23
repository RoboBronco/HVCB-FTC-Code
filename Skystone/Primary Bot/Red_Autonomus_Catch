package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
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

@Autonomous(name="Red Autonomus Catch", group="Auto")
public class Red_Autonomus_Catch extends LinearOpMode {
 
 

 
     /* Declare OpMode members. */
     RobotHardware robot = new RobotHardware();
     private ElapsedTime runtime = new ElapsedTime();
     
    ColorSensor sensorColor0;
    ColorSensor sensorColor1;

  
 
     @Override
     public void runOpMode() throws InterruptedException{
      
      try{
       
      
      
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
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.addData("imu0 calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        waitForStart();
        robot.resetAngle();

 
      if (opModeIsActive() && !isStopRequested()) {
             
             
               ///////////////////////////////////////////////////////////////
            /////////////////////   Move Forward    3 in  //////////////////second per inch 0.032
            //////////////////////////////////////////////////////////// 
             robot.forward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= .09)){
             }
            
                  ////////////////////////////////////////////////////////////
            /////////////////////   Reverse Movement  //////////////////second per inch 0.032
            ////////////////////////////////////////////////////////////
             robot.backward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= .001)){ // sleep 
             } 
             robot.stop();
              
             Thread.sleep(500);
             
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() < 390){
                 robot.ssScrew.setPower(0.5);
             }
             
            robot.ssScrew.setPower(0);
             
             robot.ssTilt.setPosition(0.75);
             Thread.sleep(250);
             
            ///////////////////////////////////////////////////////////////
            /////////////////////   Move Forward    24 in  //////////////////second per inch 0.032
            //////////////////////////////////////////////////////////// .768 - .876
             robot.forward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= .740)){
             }
            
            ////////////////////////////////////////////////////////////
            /////////////////////   Reverse Movement  //////////////////second per inch 0.032
            ////////////////////////////////////////////////////////////
             robot.backward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= .001)){
             } robot.stop();
              
             Thread.sleep(1000);
              
              
             while (opModeIsActive() && position != true){
            Color.RGBToHSV((int) (sensorColor0.red() * SCALE_FACTOR),
                    (int) (sensorColor0.green() * SCALE_FACTOR),
                    (int) (sensorColor0.blue() * SCALE_FACTOR),
                    hsvValues0);
            Color.RGBToHSV((int) (sensorColor1.red() * SCALE_FACTOR),
                    (int) (sensorColor1.green() * SCALE_FACTOR),
                    (int) (sensorColor1.blue() * SCALE_FACTOR),
                    hsvValues1);
                    
            telemetry.addData("Hue for color sensor 0", hsvValues0[0]);
            telemetry.addData("----First color Sensor ends here" , "----");
            telemetry.addData("Hue for sensor 1", hsvValues1[0]);
            telemetry.addData("----Second color Sensor ends here" , "----");
            telemetry.update();
            
            
            if (hsvValues0[0] > 105 && hsvValues1[0] < 105){
                telemetry.addData("Skystone is Left " , "yeet");
                    robot.left();
                    Thread.sleep(216);
                     robot.right();
                    Thread.sleep(1);
                    robot.stop();
                    skystoneLocation = 'l';
                    position = true;
            }   else if (hsvValues1[0] > 105 && hsvValues0[0] < 105){
                    telemetry.addData("Skystone is Middle " , "yeet");
                    robot.right();
                    Thread.sleep(261);
                    robot.left();
                    Thread.sleep(1);
                    robot.stop();
                    skystoneLocation = 'm';
                    position = true;
            }   else if(hsvValues0[0] > 105 && hsvValues1[0] > 105){
                    robot.forward();
                    runtime.reset();
                    while(opModeIsActive() && (runtime.seconds() <= 0.032)){
                    }robot.backward();
                    runtime.reset();
                    while(opModeIsActive() && (runtime.seconds() <= 0.001)){
                    }robot.stop();
            } else if (hsvValues0[0] > 105 && hsvValues1[0] > 105){
                    robot.forward();
                    runtime.reset();
                    while(opModeIsActive() && (runtime.seconds() <= 0.032)){
                    } robot.backward();
                    runtime.reset();
                    while(opModeIsActive() && (runtime.seconds() <= 0.001)){
                    }robot.stop();
            } else {
                    telemetry.addData("Skystone is Right " , "yeet");
                    robot.right();
                    Thread.sleep(616);//**Was 384** Plus 8inches or 232
                    robot.left();
                    Thread.sleep(1);
                    robot.stop();
                    skystoneLocation = 'r';
                    position = true;
            }
            
            Thread.sleep(1000);
            

////////////////////////////////////////////////////////////////////
/////////////////POSITION LEFT/////////////////////////////////////
////////////////////////////////////////////////////////////////////

            if (opModeIsActive() && skystoneLocation == 'l'){
                
                 telemetry.addData("Skystone is Left" , "!");
                telemetry.update();
                
                 // Back Up From Blocks
                robot.backward();
            Thread.sleep(102);
            robot.stop();
            Thread.sleep(100);
            
                /// -380 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -230){
                 robot.ssExtend.setPower(-0.5);
             } 
             robot.ssExtend.setPower(0);
                
                /// screw to 0 encoder
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() > 0){
                 robot.ssScrew.setPower(-0.5);
             } robot.ssScrew.setPower(0);
                
               Thread.sleep(150); 
                
                 robot.ssClaw.setPosition(1);
                 Thread.sleep(500);
                 
                 ///pull toward robot
                 while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < -50){
                 robot.ssExtend.setPower(0.5);
             } 
             robot.ssExtend.setPower(0);
                 Thread.sleep(500);
                
              
            
             
            // Spin To Foundation
                robot.resetAngle();
                Thread.sleep(100);
                robot.rotate(-80, 0.25);
                Thread.sleep(100);
                robot.spinRight();
                Thread.sleep(10);
                robot.stop();

            //robot.spinRight();
            //sleep(800);
            robot.spinLeft();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
            // Move To Foundation
            robot.forward();
            Thread.sleep(2780);
            robot.backward();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
           

            // Spin Towards Blocks
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
              
              while (opModeIsActive() && robot.ssScrew.getCurrentPosition() < 390){
                 robot.ssScrew.setPower(0.5);
             }robot.ssScrew.setPower(0);
              
              
              
                /// -430 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -430){
                 robot.ssExtend.setPower(-0.5);
             }robot.ssExtend.setPower(0);
              
                // drop  block
              robot.ssClaw.setPosition(0.6);
               Thread.sleep(500); 
               
               robot.ssTilt.setPosition(0);
             Thread.sleep(250);
              
               /// 0 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < 0){
                 robot.ssExtend.setPower(0.5);
             }robot.ssExtend.setPower(0);
              
               
          
            // spin toward foundation
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
            
            robot.right();
            Thread.sleep(600);
            robot.left();
            Thread.sleep(1);
            robot.stop();
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
            Thread.sleep(2000);
            
            robot.left(0.25);
            Thread.sleep(1500);
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
             Thread.sleep(2000);
            
            robot.spinRight(1);
            Thread.sleep(1200);         
          
          robot.right();
          Thread.sleep(1100);
          
           robot.hook0.setPosition(-0.6);
           robot.hook1.setPosition(0.6);
            
          robot.left();
          Thread.sleep(500); //was 1900
           
          robot.resetAngle();
          Thread.sleep(100);
          robot.rotate(-72, 0.3);
          Thread.sleep(100);
          
          robot.spinLeft();
          Thread.sleep(1);
          
               robot.backward();
             Thread.sleep(30);
             robot.forward();
             Thread.sleep(1);
             
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() < 0){
                 robot.ssScrew.setPower(-0.5);
             }robot.ssScrew.setPower(0);
             
            robot.backward();
          Thread.sleep(670);
           robot.stop();
            }
           
////////////////////////////////////////////////////////////////////
/////////////////POSITION MIDDLE/////////////////////////////////////
////////////////////////////////////////////////////////////////////

            if (opModeIsActive() && skystoneLocation == 'm'){
                
                telemetry.addData("Skystone is Middle" , "!");
                telemetry.update();
                
                   // Back Up From Blocks
            robot.backward();
            Thread.sleep(102);
            robot.stop();
            Thread.sleep(100);
                
                
                  /// -380 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -230){
                 robot.ssExtend.setPower(-0.5);
             } 
             robot.ssExtend.setPower(0);
                
                /// screw to 0 encoder
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() > 0){
                 robot.ssScrew.setPower(-0.5);
             } robot.ssScrew.setPower(0);
                
               Thread.sleep(150); 
                
                 robot.ssClaw.setPosition(1);
                 Thread.sleep(500);
                 
                 ///pull toward robot
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < -50){
                 robot.ssExtend.setPower(0.5);
             } 
             robot.ssExtend.setPower(0);
                 Thread.sleep(500);
                
                
           
            
            // Spin To Foundation
                robot.resetAngle();
                Thread.sleep(100);
                robot.rotate(-80, 0.25);
                Thread.sleep(100);
                robot.spinRight();
                Thread.sleep(10);
                robot.stop();

            //robot.spinRight();
            //sleep(800);
            robot.spinLeft();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
            // Move To Foundation
            robot.forward();
            Thread.sleep(2459);
            robot.backward();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
           

            // Spin Towards Blocks
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
              
            // drop  block
                 while (robot.ssScrew.getCurrentPosition() < 390){
                 robot.ssScrew.setPower(0.5);
             }robot.ssScrew.setPower(0);
              
              
              
                /// -430 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -430){
                 robot.ssExtend.setPower(-0.5);
             }robot.ssExtend.setPower(0);
              
                // drop  block
              robot.ssClaw.setPosition(0.6);
               Thread.sleep(500); 
               
               robot.ssTilt.setPosition(0);
             Thread.sleep(250);
              
               /// 0 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < 0){
                 robot.ssExtend.setPower(0.5);
             }robot.ssExtend.setPower(0);
               
          
            // spin toward foundation
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
            
 
            
            robot.right();
            Thread.sleep(600);
            robot.left();
            Thread.sleep(1);
            robot.stop();
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
            Thread.sleep(2000);
            
            robot.left(0.25);
            Thread.sleep(1500);
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
             Thread.sleep(2000);
            
            robot.spinRight(1);
            Thread.sleep(1200);         
          
          robot.right();
          Thread.sleep(1100);
          
           robot.hook0.setPosition(-0.6);
           robot.hook1.setPosition(0.6);
            
          robot.left();
          Thread.sleep(500); //was 1900
           
          robot.resetAngle();
          Thread.sleep(100);
          robot.rotate(-80, 0.3);
          Thread.sleep(100);
          
          robot.spinLeft();
          Thread.sleep(1);
          
          
              robot.backward();
             Thread.sleep(30);
             robot.forward();
             Thread.sleep(1);
             
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() > 0){
                 robot.ssScrew.setPower(-0.5);
             }robot.ssScrew.setPower(0);
             
             robot.ssTilt.setPosition(0.5);
             sleep(250);
             
            robot.backward();
          Thread.sleep(670);
           robot.stop();
            }

////////////////////////////////////////////////////////////////////
/////////////////POSITION RIGHT/////////////////////////////////////
////////////////////////////////////////////////////////////////////
             if (opModeIsActive() && skystoneLocation == 'r'){
                 
              telemetry.addData("Skystone is Right" , "!");
                telemetry.update();
                
              // Back Up From Blocks
            robot.backward();
            Thread.sleep(80);
            robot.stop();
            Thread.sleep(100);
            
           
                  /// -380 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -230){
                 robot.ssExtend.setPower(-0.5);
             } 
             robot.ssExtend.setPower(0);
                
                /// screw to 0 encoder
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() > 0){
                 robot.ssScrew.setPower(-0.5);
             } robot.ssScrew.setPower(0);
                
               Thread.sleep(150); 
                
                 robot.ssClaw.setPosition(1);
                 sleep(500);
                 
                 ///pull toward robot
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < -50){
                 robot.ssExtend.setPower(0.5);
             } 
             robot.ssExtend.setPower(0);
                 Thread.sleep(500);
                
            
            // Spin To Foundation
                robot.resetAngle();
                Thread.sleep(100);
                robot.rotate(-80, 0.25);
                Thread.sleep(100);
                robot.spinRight();
                Thread.sleep(10);
                robot.stop();

            //robot.spinRight();
            //sleep(800);
            robot.spinLeft();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
            // Move To Foundation **Was 2600** Subtract 16inches
            robot.forward();
            Thread.sleep(2220);
            robot.backward();
            Thread.sleep(1);
            robot.stop();
            Thread.sleep(100);
            
           

            // Spin Towards Blocks
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
              
            // drop  block
                 while (robot.ssScrew.getCurrentPosition() < 390){
                 robot.ssScrew.setPower(0.5);
             }robot.ssScrew.setPower(0);
              
              
              
                /// -430 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() > -430){
                 robot.ssExtend.setPower(-0.5);
             }robot.ssExtend.setPower(0);
              
                // drop  block
              robot.ssClaw.setPosition(0.6);
               Thread.sleep(500); 
               
               robot.ssTilt.setPosition(0);
             Thread.sleep(250);
              
               /// 0 for extension  encoder
             while (opModeIsActive() && robot.ssExtend.getCurrentPosition() < 0){
                 robot.ssExtend.setPower(0.5);
             }robot.ssExtend.setPower(0);
             
             
          
            // spin toward foundation
            robot.resetAngle();
            Thread.sleep(100);
            robot.rotate(80,0.25);/// left
            Thread.sleep(100);
            robot.spinRight();
            Thread.sleep(1);
             robot.stop();
              Thread.sleep(100);
            
             
            
            robot.right();
            Thread.sleep(600);
            robot.left();
            Thread.sleep(1);
            robot.stop();
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
            Thread.sleep(2000);
            
            robot.left(0.25);
            Thread.sleep(1500);
            
            robot.hook0.setPosition(1);
            robot.hook1.setPosition(-1);
             Thread.sleep(2000);
            
            robot.spinRight(1);
            Thread.sleep(1200);         
          
          robot.right();
          Thread.sleep(1100);
          
           robot.hook0.setPosition(-0.6);
           robot.hook1.setPosition(0.6);
            
          robot.left();
          Thread.sleep(500); //was 1900
           
          robot.resetAngle();
          Thread.sleep(100);
          robot.rotate(-75, 0.3);
          Thread.sleep(100);
          
          robot.spinLeft();
          Thread.sleep(1);
          
           robot.backward();
             Thread.sleep(30);
             robot.forward();
             Thread.sleep(1);
             robot.stop();
             
             while (opModeIsActive() && robot.ssScrew.getCurrentPosition() > 0){
                 robot.ssScrew.setPower(-0.5);
             }robot.ssScrew.setPower(0);
             
             robot.ssTilt.setPosition(0.5);
             Thread.sleep(250);
             
            robot.backward();
          Thread.sleep(670);
           robot.stop();
            }
           
            
          telemetry.update();
          }
            
 
      }
 
} catch(InterruptedException e){
 telemetry.addLine("Hey Caught Interruption");
 telemetry.update();
 robot.stop();
 robot.ssExtend.setPower(0);
 robot.ssScrew.setPower(0);
 Thread.sleep(500);
}
    
            }
            
             
            }
            
