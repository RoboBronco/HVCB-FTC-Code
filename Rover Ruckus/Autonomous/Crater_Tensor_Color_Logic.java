package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import android.graphics.Color;

@Autonomous(name="Crater Tensor Color Logic", group="Auto")
public class Crater_Tensor_Color_Logic extends LinearOpMode {
 
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    // private TFObjectDetector tfod;

 
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
     robot.initVuforia();
    robot.initTfod();
     // Wait for the game to start (driver presses PLAY)
    
     
  

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

          // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;


     
            /** Activate Tensor Flow Object Detection. */
            if (robot.tfod != null) {
                robot.tfod.activate();
            }

            if (opModeIsActive()) {
                
              //// STEP 1: Move lift UP to lower robot from hanging position to playing field 
              //// Negative lift power = extending
              robot.reset();
              robot.lift.setPower(-1);
              boolean done = false;
              while(!done && opModeIsActive() && !isStopRequested()){
               if (robot.lift.getCurrentPosition() < -9449) {  ////////////////////////// change encoder value
                  done = true;
               }
               telemetry.addData("Encoder Value is: ", robot.avgencoderval());
               telemetry.update();
              }
              robot.lift.setPower(0);
              robot.stop();
              sleep(500);
             
              //move right to detatch from lander
              robot.right();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= 0.16)){
             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
             }robot.stop();
             sleep(500);
             // move backward to square agaist the lander
             
             robot.backward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= 0.05)){
             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
             }robot.stop();
             sleep(500);
     
             robot.forward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= 0.1)){
             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
             }robot.stop();
              robot.backward();
             runtime.reset();
             while(opModeIsActive() && (runtime.seconds() <= 0.01)){
             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
             }robot.stop();
             sleep(500);     
     
            /** Activate Tensor Flow Object Detection. */
            if (robot.tfod != null) {
                robot.tfod.activate();
            }
                
            while (opModeIsActive() && runtime.seconds() <= 4) {
                if (robot.tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = robot.tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      int position = 0;
                      telemetry.addData("# Object Detected", updatedRecognitions.size());
                      if (updatedRecognitions.size() >= 2) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                          if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                          } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                          }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1) {
                          if (goldMineralX > silverMineral1X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                            telemetry.update();
                             // spin right to face gold mineral
                             robot.spinRight();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.3)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                                  robot.spinLeft();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             sleep(250);
                             
                             //move forward to knock block off position RIGHT
                             robot.forward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.85)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                               robot.backward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             
                             // move back after hitting position 1
                               robot.backward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.36)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                               robot.forward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             sleep(250);
                             
                             // spin right to to aim at depot wall
                              robot.spinRight();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.52)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             robot.spinLeft();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             sleep(250);
                             
                             // move backward at wall
                             robot.backward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 1.57)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             robot.backward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             sleep(250);
                             
                             //spin left to parrellel to wall
                             robot.spinLeft();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.32)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }robot.stop();
                             sleep(250);
                             
                             // strafe left into wall
                              robot.left();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.33)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                              sleep(250);
                             runtime.reset();
                             robot.right();
                            while(opModeIsActive() && (runtime.seconds() <= 0.04)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                             runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                        
                        
                             //MOVE BACK AND WAIT FOR RED or Blue
                            double hsvVal = 100;
                            runtime.reset();
                            robot.backward();
                            sleep(400);
                            while (opModeIsActive() && (runtime.seconds() <= 8) && ((hsvVal > 15) && (hsvVal < 175))){
                                 Color.RGBToHSV( (robot.sensorColor.red()),
                                 (robot.sensorColor.green()),
                                 (robot.sensorColor.blue()),
                                 hsvValues); 
                                 hsvVal = hsvValues[0];
                        
                            telemetry.addData("Hue", hsvValues[0]);
                            telemetry.update();
                            
                            }
                            
                            sleep(180); 
                          robot.marker.setPosition(0.9);
                          robot.forward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                             telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                             robot.stop();
                          
                          sleep (1000);
                         robot.stop();

                            runtime.reset();
                             robot.right();
                            while(opModeIsActive() && (runtime.seconds() <= 0.06)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                             runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();

                        robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                         
                         telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                         
                          runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.06)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                             
                              runtime.reset();
                             robot.forward();
                            while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                            sleep(100);
                            robot.marker.setPosition(0);
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                             robot.forward(0.1);
                             
                             ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////RUN HARVESTERS//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        runtime.reset();
                        robot.collector1.setPosition(1);
                        robot.collector2.setPosition(0);
                        robot.collectorHarvesterFlip.setPower(-1.5);
                        while(opModeIsActive() && (runtime.seconds() <= 1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////FLIP HARVESTER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        robot.collectorHarvesterFlip.setPower(0);
                        runtime.reset();
                        robot.collectorArmExtend.setPower(-1);
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////VACUUM CLEANER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 0.1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                         robot.collector1.setPosition(0.5);
                        robot.collector2.setPosition(0.5);
                        robot.stop();
                        // WAIT //
                        sleep(5000);

                          
                            
                            position = 1;
                            telemetry.update();
                            

                            
                          } else if (goldMineralX < silverMineral1X) {
                            telemetry.addData("Gold Mineral Position", "Center");
                            telemetry.update();
                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            ///////////////////////////////////KNOCK BLOCK OFF//////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            robot.forward();
                            runtime.reset();
                            while(opModeIsActive() && (runtime.seconds() <= 0.8)){
                            telemetry.addData("KNOCK BLOCK", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.backward();
                            while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                            telemetry.addData("KNOCK BLOCK", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                            sleep(250);
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            /////////////////////////////////MOVE BACK FROM BLOCK/////////////////////////////////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            robot.backward();
                            runtime.reset();
                            while(opModeIsActive() && (runtime.seconds() <= 0.24)){
                            telemetry.addData("BACK FROM BLOCK", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.forward();
                            while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                            telemetry.addData("BACK FROM BLOCK", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                            sleep(250);
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            /////////////////////////////////SPIN TO FACE DEPOT////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            robot.spinRight();
                            runtime.reset();
                            while(opModeIsActive() && (runtime.seconds() <= 0.8)){
                            telemetry.addData("FACE DEPOT", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.spinLeft();
                            while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                            telemetry.addData("FACE DEPOT", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                            sleep(250);
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            /////////////////////////////////FORWARD TO WALL////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            robot.backward();
                            runtime.reset();
                            while(opModeIsActive() && (runtime.seconds() <= 1.57)){
                            telemetry.addData("FORWARD to WALL", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.forward();
                            while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                            telemetry.addData("FORWARD to WALL", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                            sleep(250);
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            /////////////////////////////////SPIN TO FACE WALL////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            robot.spinLeft();
                            runtime.reset();
                            while(opModeIsActive() && (runtime.seconds() <= 0.29)){
                            telemetry.addData("SPIN to WALL", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.spinRight();
                            while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                            telemetry.addData("SPIN to WALL", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                            sleep(250);
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            //////////////////////////////Strafe to Wall//////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                             robot.left();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.33)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                              sleep(250);
                             runtime.reset();
                             robot.right();
                            while(opModeIsActive() && (runtime.seconds() <= 0.04)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                             runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                        
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            //////////////////////////////FOWARD TO DROP MARKER/////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    //MOVE BACK AND WAIT FOR RED or Blue
                            double hsvVal = 100;
                            runtime.reset();
                            robot.backward();
                            sleep(400);
                            while (opModeIsActive() && (runtime.seconds() <= 8) && ((hsvVal > 15) && (hsvVal < 175))){
                                 Color.RGBToHSV( (robot.sensorColor.red()),
                                 (robot.sensorColor.green()),
                                 (robot.sensorColor.blue()),
                                 hsvValues); 
                                 hsvVal = hsvValues[0];
                        
                            telemetry.addData("Hue", hsvValues[0]);
                            telemetry.update();
                            
                            }
                            
                            sleep(180); 
                          robot.marker.setPosition(0.9);
                          robot.forward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                             telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                             robot.stop();
                         
                          sleep (1000);
                         robot.stop();

                         robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                         
                         telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                         
                          runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.06)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                             
                              runtime.reset();
                             robot.forward();
                            while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                            sleep(100);
                            robot.marker.setPosition(0);
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                             robot.forward(0.1);
                             
                         
                           ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////RUN HARVESTERS//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        runtime.reset();
                        robot.collector1.setPosition(1);
                        robot.collector2.setPosition(0);
                        robot.collectorHarvesterFlip.setPower(-1.5);
                        while(opModeIsActive() && (runtime.seconds() <= 1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////FLIP HARVESTER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        robot.collectorHarvesterFlip.setPower(0);
                        runtime.reset();
                        robot.collectorArmExtend.setPower(-1);
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////VACUUM CLEANER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 0.1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                         robot.collector1.setPosition(0.5);
                        robot.collector2.setPosition(0.5);
                        robot.stop();
                        // WAIT //
                        sleep(5000);

                          
                        
                            position = 2;
                            telemetry.update();
                          }

                          
                        }else{
                          telemetry.addData("Gold Mineral Position", "Left");
                         telemetry.update();
                         
                         //////////////LEFT//////////////
                         robot.left();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.12)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                         // spin left to face gold mineral
                          robot.spinLeft();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.3)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                         robot.spinRight();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                          //move forward to knock block off position Left
                         robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.86)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                           robot.backward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         
                         // move back after hitting Left Mineral
                         robot.backward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.48)){
                         telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                         robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                         telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                        // spin right to to aim at depot wall
                          robot.spinRight();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 1.02)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         robot.spinLeft();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                         // move backward at wall
                         robot.backward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 1.09)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                         //spin left to parrellel to wall
                         robot.spinLeft();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 0.32)){
                         //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }robot.stop();
                         sleep(250);
                         
                           ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            //////////////////////////////Strafe to Wall//////////////////////////////////////////////////////////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                             robot.left();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.33)){
                             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                              sleep(250);
                             runtime.reset();
                             robot.right();
                            while(opModeIsActive() && (runtime.seconds() <= 0.04)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }

                             runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.01)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
           
                         sleep(500);
                         
                         
                                 //MOVE BACK AND WAIT FOR RED or Blue
                            double hsvVal = 100;
                            robot.backward();
                            sleep(400);
                            runtime.reset();
                            while (opModeIsActive() && (runtime.seconds() <= 8) && ((hsvVal > 15) && (hsvVal < 175))){
                                 Color.RGBToHSV( (robot.sensorColor.red()),
                                 (robot.sensorColor.green()),
                                 (robot.sensorColor.blue()),
                                 hsvValues); 
                                 hsvVal = hsvValues[0];
                        
                            telemetry.addData("Hue", hsvValues[0]);
                            telemetry.update();
                            
                            }
                            
                            sleep(180); 
                          robot.marker.setPosition(0.9);
                          robot.forward();
                             runtime.reset();
                             while(opModeIsActive() && (runtime.seconds() <= 0.02)){
                             telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                             }
                          
                          robot.stop();
                          sleep (1000);
                         robot.stop();
                         
                         robot.forward();
                         runtime.reset();
                         while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                         
                         telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
                         }
                         robot.stop();
                         
                          runtime.reset();
                             robot.left();
                            while(opModeIsActive() && (runtime.seconds() <= 0.06)){
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                            robot.stop();
                             
                              runtime.reset();
                             robot.forward();
                            while(opModeIsActive() && (runtime.seconds() <= 1.25)){
                            sleep(100);
                            robot.marker.setPosition(0);
                            telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                            }
                             robot.forward(0.1);
                         
                          ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////RUN HARVESTERS//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        runtime.reset();
                        robot.collector1.setPosition(1);
                        robot.collector2.setPosition(0);
                        robot.collectorHarvesterFlip.setPower(-1.5);
                        while(opModeIsActive() && (runtime.seconds() <= 1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////FLIP HARVESTER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        robot.collectorHarvesterFlip.setPower(0);
                        runtime.reset();
                        robot.collectorArmExtend.setPower(-1);
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////VACUUM CLEANER//////////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // CONTRACT //
                        robot.collectorArmExtend.setPower(-1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 1.8)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                        
                        // EXTEND //
                        robot.collectorArmExtend.setPower(1);
                        runtime.reset();
                        while(opModeIsActive() && (runtime.seconds() <= 0.1)){
                        telemetry.addData("Drop Marker", "Time: %2.5f S Elapsed", runtime.seconds());
                        }
                         robot.collector1.setPosition(0.5);
                        robot.collector2.setPosition(0.5);
                        robot.stop();
                        // WAIT //
                        sleep(5000);

                          
                          position = 3;
                          telemetry.update();
                        }
                      }
                      telemetry.update();
                      idle();
                    }
                }
            }
            
        }

        if (robot.tfod != null) {
           robot.tfod.shutdown();
           
           
    
           
        }
    // }

    // /**
    //  * Initialize the Vuforia localization engine.
    //  */
    // private void initVuforia() {
    //     /*
    //      * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
    //      */
    //     VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    //     parameters.vuforiaLicenseKey = VUFORIA_KEY;
    //     parameters.cameraName = hardwareMap.get(WebcamName.class, "tensorWebcam");

    //     //  Instantiate the Vuforia engine
    //     vuforia = ClassFactory.getInstance().createVuforia(parameters);

    //     // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    // }
}
    /**
      * Initialize the Tensor Flow Object Detection engine.
      */
    // private void initTfod() {
    //     int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
    //         "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    //     TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    //     tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    //     tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    // }
    

}
