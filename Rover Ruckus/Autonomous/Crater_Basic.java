import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
 
@Autonomous(name="Basic Crater", group="Auto")
public class Crater_Basic extends LinearOpMode {
 
/* Declare OpMode members. */
RoverHardware robot = new RoverHardware();
private ElapsedTime runtime = new ElapsedTime();
 
@Override
public void runOpMode() {
 
/*
* Initialize the drive system variables.
* The init() method of the hardware class does all the work here
*/
robot.init(hardwareMap);
 
// Send telemetry message to signify robot waiting;
telemetry.addData("Status: ", "Ready to Deploy!"); //
telemetry.update();
// Wait for the game to start (driver presses PLAY)
waitForStart();
 
////////////////////////////////////////////////////////////////
///// Begin routine hanging from lander facing CRATER //////
////////////////////////////////////////////////////////////////
 
//// STEP 1: Move lift UP to lower robot from hanging position to playing field 
//// Negative lift power = extending
robot.lift.setPower(-1);
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 3.4)){
telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.lift.setPower(0);
sleep(1000);
 
////  STEP 2: Move right to detach the hook from the lander ring
robot.right();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.1)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}robot.stop();
sleep(1000);
 

//// STEP 3: Move backward to square the robot against the lander
robot.backward();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.03)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}robot.stop();
sleep(1000);
 
//// STEP 4: Move forward away from lander toward mineral samples
robot.forward();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.61)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
 robot.stop();
//// STEP 4A: Reverse move (backward) to stop the forward movement above
robot.backward();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
sleep (1000);

 
             ////STEP 5: Move right to position robot at mineral sample 1
robot.right();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.52)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
////STEP 5A: Reverse move (left) to stop the movement above
robot.left();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
sleep (1000);
 
///////////////////////////////////////////////
///// Robot is now at mineral sample 1 //////
///////////////////////////////////////////////
///// Add servo arm movement here/////////
///////////////////////////////////////////////
///// Add sensor sampling logic here/////////
///// If gold move forward 5”, then back 5”, then to Start Depot Move Position //
///// If not gold continue to next step////////
///////////////////////////////////////////////
 
////STEP 6: Move left from mineral sample 1 to mineral sample 2
robot.left();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.65)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
////STEP 6A: Reverse move (right) to stop the movement above
robot.right();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
sleep (1000);
 
///////////////////////////////////////////////
///// Robot is now at sample mineral 2 //////
///////////////////////////////////////////////
///// Add sensor sampling logic here/////////
///// If gold move forward 5”, then back 5”, then to Start Depot Move Position //
///// If not gold continue to next step////////
///////////////////////////////////////////////
 
//// STEP 7: Move left from mineral sample 2 to mineral sample 3
robot.left();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.65)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
//// STEP 7A: Reverse move (right) to stop the movement above
robot.right();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
sleep (1000);
 
///////////////////////////////////////////////
///// Robot is now at sample mineral 3 //////
///////////////////////////////////////////////
///// Add sensor sampling logic here/////////
///// If gold move forward 5”, then back 5”, then to Start Depot Move Position//
///// If not gold continue to next step////////
///////////////////////////////////////////////
 
/////////////////////////////////////////////
///// THIS IS “START DEPOT MOVE POSITION”(blue box) ///////////////////
///// Robot is now done sampling and at sample mineral 3////
///// Begin movement toward depot to drop marker////
/////////////////////////////////////////////
 
//// STEP 8: Move left toward depot wall at ~45 degrees to wall
robot.left();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 1.2)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
////STEP 8A:  Reverse move (right) to stop the movement above
robot.right();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
 
//// STEP 9: Spin right to square the robot to the wall before depot move
robot.spinRight();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.37)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
////STEP 9A:  Reverse move (spin left) to stop the movement above
robot.spinLeft();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
 
////STEP 10: Move backward toward depot to drop the marker
robot.backward();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 1.28)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
////STEP 10A: Reverse move (forward) to stop movement above
robot.forward();
robot.stop();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <= 0.02)){
//telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
 
/////////////////////////////////////////////
//// Robot is now in depot ////
/////////////////////////////////////////////
 
//// STEP 11: Spin left to make sure the marker drops in the center of the depot
robot.spinLeft();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <=0.16)){
telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
//// STEP 11A:  Reverse move (Spin right) to stop movement above
robot.spinRight();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <=0.01)){
telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
 
//////////////////////////////////////////////
//// Drop the marker ////
//////////////////////////////////////////////
 
//// STEP 12: Rotate marker servo to drop servo
robot.marker.setPosition(0.75);
 
////STEP 13 Sleep robot for enough time to drop marker SUPER IMPORTANT SLEEP!!!
sleep (2000);
 
//// STEP 14: Spin right to aim the robot back at the crater before moving there (spin above +.01)
robot.spinRight();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <=0.17)){
telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
//// STEP 14A:  Reverse move (Spin Left) to stop movement above
robot.spinLeft();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <=0.01)){
telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();



 
//// STEP 15: Move forward from depot toward crater
robot.forward();
runtime.reset();
while(opModeIsActive() && (runtime.seconds() <=2)){
telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
}
robot.stop();
 
robot.marker.setPosition(0);
 
////////////////////////////////////////////////////
///// Robot is now touching crater /////
///// End autonomous /////
////////////////////////////////////////////////////
 
 
///////////////////////////////////////////////////////
///// Sampling code to be tested and used later/////
///////////////////////////////////////////////////////
 
//// hsvValues is an array that will hold the hue, saturation, and value information.
////float hsvValues[] = {0F, 0F, 0F};
 
//// values is a reference to the hsvValues array.
////final float values[] = hsvValues;
 
//// sometimes it helps to multiply the raw RGB values with a scale factor
//// to amplify/attentuate the measured values.
////final double SCALE_FACTOR = 255;
 
////boolean goldColor = false;
////if(hsvValues[0] < 40){
////goldColor = true;
////telemetry.addData("Bannana color", goldColor);
////telemetry.addData("Hue", hsvValues[0]);
////}else if (hsvValues[0] > 120){
////goldColor = false;
////telemetry.addData("Not Bannana Color", goldColor);
////telemetry.addData("Hue", hsvValues[0]);
////} else {
////goldColor = false;
////telemetry.addData("Neither", goldColor);
////telemetry.addData("Hue", hsvValues[0]);
////}
////telemetry.update();
////sleep(10000);
 
//// if (boolean goldColor = true); {
//// while (opModeIsActive(); && runtime.seconds() < 1); {
//// robot.forward();
 
//// }
 
//// Drive towards to sample
//// robot.forward();
//// runtime.reset();
//// while(opModeIsActive() && (runtime.seconds() <= 0.8)) {
//// telemetry.addData("Arriving", "Time: %2.5f S Elapsed", runtime.seconds());
//// }
//// robot.stop();
////UNCOMMENT FOR SAMPLING
//// robot.sample.setPosition(0.5);
//// Add color logic here at a later time
 }
}
