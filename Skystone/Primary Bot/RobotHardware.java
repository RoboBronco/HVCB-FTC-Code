package FTC_2019_2020_Season;

import android.nfc.cardemulation.OffHostApduService;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import java.lang.Math;
import android.graphics.Color;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import android.util.Log;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import java.util.List;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.ClassFactory;

public class RobotHardware
{
    
    
    
    static final double INCREMENT   = 0.03;     // amount to ramp motor each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_FWD     =  1.0;     // Maximum FWD power applied to motor
    static final double MAX_REV     = -1.0;     // Maximum REV power applied to motor
    
    double  rampUpPower   = 0; ///// for ramp down
    boolean rampUp  = false; //// for ramp down
     
    
  
    /* Public OpMode members. */
    public DcMotor  fl   = null;
    public DcMotor  fr  = null;
    public DcMotor  bl   = null;
    public DcMotor  br  = null;
    public DcMotor  ssScrew   = null;
    public DcMotor  ssExtend   = null;
    
   
    public Servo    hook0 = null;
    public Servo    hook1 = null;
    //public Servo    ssTwist = null;
    public Servo    ssTilt = null;
    public Servo    ssClaw = null;
    public Servo    blockBlue = null;
    public Servo    blockRed = null;

    
    public ColorSensor sensorColor0 = null;
    public ColorSensor sensorColor1 = null;
    
    

    
    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    double globalAngle, power = .3;
    
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
     
       fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     
    //  ssExtend.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );

        
      
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
        //ssTwist = hwMap.get(Servo.class, "ssTwist");
        ssClaw = hwMap.get(Servo.class, "ssClaw");
        blockBlue = hwMap.get(Servo.class, "blockBlue");
        blockRed = hwMap.get(Servo.class, "blockRed");

       
        hook0.setPosition(-0.6);
        hook1.setPosition(0.6);
       // ssTwist.setPosition(0.5);
        ssClaw.setPosition(0.55);
        blockBlue.setPosition(0.45);
        blockRed.setPosition(0.55);
        // blockBlue.setPosition(0);
        // blockRed.setPosition(1);

       
        sensorColor0 = hwMap.get(ColorSensor.class, "sensorColor0");
        sensorColor1 = hwMap.get(ColorSensor.class, "sensorColor1");
        
        //String verticalLeftEncoderName = br, verticalRightEncoderName = fl, horizontalEncoderName = fr;
        //String leftYEncoder = br, rightYEncoder = fl, centerXEncoder = fr;
        
        // leftYEncoder = hardwareMap.dcMotor.get(br);
         //rightYEncoder = hardwareMap.dcMotor.get(fl);
        // centerXEncoder = hardwareMap.dcMotor.get(fr);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu = hwMap.get(BNO055IMU.class, "imu0");

        imu.initialize(parameters);

        reset();
       
    }
    double cut = 0;

    // // Methods
    public void forwardByEncoder(double speed, double distance){
        // while (((-bl.getCurrentPosition() < distance)) && (distance - -bl.getCurrentPosition)){
        reset();
        while (((-bl.getCurrentPosition() < distance))){
            
        //  fl.setPower(FORWARD);
        //  fr.setPower(FORWARD);
        //  bl.setPower(FORWARD);
        //  br.setPower(FORWARD);
        fl.setPower(speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);
        }
        fl.setPower(OFF);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(OFF);
    }    
  
    // Variable speed was FORWARD
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////  Ramp Up Test /////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
      public void forwardByEncoderRamp(double speed, double distance){
        // while (((-bl.getCurrentPosition() < distance)) && (distance - -bl.getCurrentPosition)){
        while ((-bl.getCurrentPosition() < distance)){
        fl.setPower(speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);
        
        if (distance >= 650) { 
         if (Math.abs(distance) - Math.abs(bl.getCurrentPosition()) <= 400) {
            //  fl.setPower(OFF);
            //  fr.setPower(OFF);
            //  bl.setPower(OFF);
            //  br.setPower(OFF);
        // Keep stepping down until we hit the min value.
                speed -= INCREMENT;
                if (rampUpPower <= 0 ) {
                rampUpPower = 0;
                }
            }
        }
        }
        fl.setPower(OFF);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(OFF);
      }
    
    public void forwardByEncoderV2(double speed, double distance){
        reset();
        while(bl.getCurrentPosition()/307.699557 < distance){ // inches
            double distanceToTarget = distance - -bl.getCurrentPosition()/307.699557; //inches
            // Uses speed to determine distance to slow down
            double rampDownTrigger = speed*4; // Ten because 1speed equals 10 inches, .5 = 5, so on and so forth
            
            // Uses rampDownTrigger to determine variable to slow down by
            double rampDownMultiplier = 1/rampDownTrigger;
            if ((distanceToTarget < rampDownTrigger) && (distanceToTarget > 1)){ //inches
                double newSpeed = distanceToTarget*speed*rampDownMultiplier; //Determines Ramp Down Speed
                fl.setPower(newSpeed);
                fr.setPower(newSpeed);
                bl.setPower(newSpeed);
                br.setPower(newSpeed);
            } else if(distanceToTarget < 1){                    //This sets a minimum speed
                fl.setPower(0.1);
                fr.setPower(0.1);
                bl.setPower(0.1);
                br.setPower(0.1);
            } else{                                             // This is driving at normal speed
                fl.setPower(speed);
                fr.setPower(speed);
                bl.setPower(speed);
                br.setPower(speed);
            }                                                  // Once we reach our target, we exit the loop
        }                                                      // This turns the wheels off
                fl.setPower(OFF);
                fr.setPower(OFF);
                bl.setPower(OFF);
                br.setPower(OFF);
    }
    
    
    // Method for encoder BACKWARD mpovement
     public void backwardByEncoder(double speed, double distance){
         reset();
         // while (((-bl.getCurrentPosition() < distance)) && (distance - -bl.getCurrentPosition)){
         while (((-bl.getCurrentPosition() > distance))){
         fl.setPower(-speed);
         fr.setPower(-speed);
         bl.setPower(-speed);
         br.setPower(-speed);
         // -speed was BACKWARD
        
         }
         fl.setPower(OFF);
         fr.setPower(OFF);
         bl.setPower(OFF);
         br.setPower(OFF);
     }    
    
    
    // Method for encoder RIGHT movement
     public void rightByEncoder(double speed, double distance){
        reset();
        while (((fr.getCurrentPosition() < distance))){
        // Note: +speed was FORWARD, -speed was BACK
        fl.setPower(speed);
        fr.setPower(-speed);
        bl.setPower(-speed);
        br.setPower(speed);
        }
         fl.setPower(OFF);
         fr.setPower(OFF);
         bl.setPower(OFF);
         br.setPower(OFF);
     }    
    
    
    // Method for encoder LEFT mpovement
     public void leftByEncoder(double speed, double distance){
         reset();
         while (((fr.getCurrentPosition() > distance))){
            // Note: +speed was FORWARD, -speed was BACK
            fl.setPower(-speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(-speed);
        // fl.setPower(-Math.abs(speed));
        // fr.setPower(Math.abs(speed));
        // bl.setPower(Math.abs(speed));
        // br.setPower(-Math.abs(speed)); 
         }
         fl.setPower(OFF);
         fr.setPower(OFF);
         bl.setPower(OFF);
         br.setPower(OFF);
     }    

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
    
        // Method for not moving
    public void stopExc() throws InterruptedException{
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
    
    public double getLeftYEncoder(){
        return -br.getCurrentPosition();
    }
    
    public double getRightYEncoder(){
        return -bl.getCurrentPosition();
    }
 
    
        /*
     * Resets the cumulative angle tracking to zero.
     */
    public void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    public double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    public double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     * @param degrees Degrees to turn, + is left - is right
     */
    public void rotate(int degrees, double power)
    {
        double  turnPower;
        double angle = getAngle();
        resetAngle();


        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < angle)
        {   // turn right.
            turnPower = -power;
        }
        else if (degrees > angle)
        {
            turnPower = power;
        }
        else return;

        // set power to rotate.
        fl.setPower(-turnPower);
        bl.setPower(-turnPower);
        fr.setPower(turnPower);
        br.setPower(turnPower);

        // rotate until turn is completed.
        if (degrees < angle)
        {
            // On right turn we have to get off zero first.
            while (getAngle() == angle) {}

            while (getAngle() > degrees) {}
        }
        else    // left turn.
            while (getAngle() < degrees) {}


        // rotate until turn is completed.
        if (degrees < angle)
        {
            // On right turn we have to get off zero first.
            while (getAngle() == angle) {}

            while (getAngle() > degrees) {}
        }
        else    // left turn.
            while (getAngle() < degrees) {}


        // turn the motors off.
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        // wait for rotation to stop.
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

} 
