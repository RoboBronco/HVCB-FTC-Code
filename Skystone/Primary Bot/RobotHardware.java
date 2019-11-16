package FTC_2019_2020_Season;

import android.nfc.cardemulation.OffHostApduService;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import java.lang.Math;
import android.graphics.Color;

public class RobotHardware
{
    /* Public OpMode members. */
    public DcMotor  fl   = null;
    public DcMotor  fr  = null;
    public DcMotor  bl   = null;
    public DcMotor  br  = null;
    public DcMotor  ssScrew   = null;
    public DcMotor  ssExtend   = null;
   
    public Servo    hook0 = null;
    public Servo    hook1 = null;
    public Servo    ssTwist = null;
    public Servo    ssTilt = null;
    public Servo    ssClaw = null;
    
    public ColorSensor sensorColor0 = null;
    public ColorSensor sensorColor1 = null;
    
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    // Variables for Drive Speed
    final double FORWARD = 0.5;
    final double BACK    = -0.5;
    final int OFF        = 0;

    /* Constructor */
    public RobotHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        fl  = hwMap.get(DcMotor.class, "fl");
        fr = hwMap.get(DcMotor.class, "fr");
        bl  = hwMap.get(DcMotor.class, "bl");
        br = hwMap.get(DcMotor.class, "br");
        ssScrew = hwMap.get(DcMotor.class, "ssScrew");
        ssExtend = hwMap.get(DcMotor.class, "ssExtend");
      
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
     
      
        // Set all motors to zero power
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        ssScrew.setPower(0);
        ssExtend.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ssScrew.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ssExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        hook0 = hwMap.get(Servo.class, "hook0");
        hook1 = hwMap.get(Servo.class, "hook1");
        ssTilt = hwMap.get(Servo.class, "ssTilt");
        ssTwist = hwMap.get(Servo.class, "ssTwist");
        ssClaw = hwMap.get(Servo.class, "ssClaw");
       
        hook0.setPosition(-1);
        hook1.setPosition(1);
        ssTwist.setPosition(0.5);
        ssClaw.setPosition(0.5);
       
        sensorColor0 = hwMap.get(ColorSensor.class, "sensorColor0");
        sensorColor1 = hwMap.get(ColorSensor.class, "sensorColor1");
     
        reset();
       
    }
    double cut = 0;

    // Methods
    
  
    
    // Method for driving forwards -- default
    public void forward(){
        fl.setPower(FORWARD);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(FORWARD);
    }

    // Method for driving forwards -- select speed
    public void forward(double speed){ 
        fl.setPower(Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for driving backwards -- default
    public void backward(){
        fl.setPower(BACK);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(BACK);
    }

    // Method for driving backwards -- select speed
    public void backward(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for driving left -- default
    public void left(){
        fl.setPower(BACK);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(BACK);
    }

    // Method for driving left -- select speed
    public void left(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for driving right -- default
    public void right(){
        fl.setPower(FORWARD);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(FORWARD);
    }

    // Method for driving right -- select speed
    public void right(double speed){
        fl.setPower(Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for driving diagonal forward left -- default
    public void forwardLeft(){
        fl.setPower(FORWARD);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(FORWARD);
    }

    // Method for driving diagonal forward left -- select speed
    public void forwardLeft(double speed){
        fl.setPower(Math.abs(speed));
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(Math.abs(speed));
    }

    // Method for driving diagonal back left -- default
    public void backLeft(){
        fl.setPower(OFF);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(OFF);
    }

    // Method for driving diagonal back left -- select speed
    public void backLeft(double speed){
        fl.setPower(OFF);
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(OFF);
    }

    // Method for driving diagonal forward right
    // Default
    public void forwardRight(){
        fl.setPower(OFF);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(OFF);
    }

    // Method for driving diagonal forward right
    // Select Speed
    public void forwardRight(double speed){
        fl.setPower(OFF);
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(OFF);
    }

    // Method for driving diagonal back right
    // Default
    public void backRight(){
        fl.setPower(BACK);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(BACK);
    }

    // Method for driving diagonal back right
    // Select Speed
    public void backRight(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(-Math.abs(speed));
    }

    // Method for spinning left -- default
    public void spinLeft(){
        fl.setPower(BACK);
        fr.setPower(FORWARD);
        bl.setPower(BACK);
        br.setPower(FORWARD);
    }

    // Method for spinning left -- select speed
    public void spinLeft(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for spinning right -- default
    public void spinRight(){
        fl.setPower(FORWARD);
        fr.setPower(BACK);
        bl.setPower(FORWARD);
        br.setPower(BACK);
    }

    // Method for spinning right -- select speed
    public void spinRight(double speed) {
        fl.setPower(Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for not moving
    public void stop() {
        fl.setPower(OFF);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(OFF);
    }
    
    public void reset () {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ssScrew.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ssExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ssScrew.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ssExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    
 
    
   
     
     

} 
 
