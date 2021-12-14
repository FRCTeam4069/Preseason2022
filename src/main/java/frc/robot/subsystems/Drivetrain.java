package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    
    Constants constants = new Constants();

    GEAR currentGear;

    private CANSparkMax leftMaster;
    private CANSparkMax leftSlave;
    private CANSparkMax rightMaster;
    private CANSparkMax rightSlave;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private DoubleSolenoid shifter;

    public Drivetrain() {
        leftMaster = new CANSparkMax(constants.DT_LEFT_MASTER_ID, MotorType.kBrushless);
        leftSlave = new CANSparkMax(constants.DT_LEFT_SLAVE_ID, MotorType.kBrushless);
        rightMaster = new CANSparkMax(constants.DT_RIGHT_MASTER_ID, MotorType.kBrushless);
        rightSlave = new CANSparkMax(constants.DT_RIGHT_SLAVE_ID, MotorType.kBrushless);

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftEncoder = new Encoder(constants.DT_LEFT_ENC_A, constants.DT_LEFT_ENC_B, true, EncodingType.k1X);
        rightEncoder = new Encoder(constants.DT_RIGHT_ENC_A, constants.DT_RIGHT_ENC_B, false, EncodingType.k1X);

        leftEncoder.setDistancePerPulse(1);
        rightEncoder.setDistancePerPulse(1);

        shifter = new DoubleSolenoid(constants.DT_SHIFTER_F, constants.DT_SHIFTER_B);

        currentGear = GEAR.LOW;
    }


    public void basicDrive(double velocityPercent, double turnPercent) {
        double leftOutput;
        double rightOutput;

        leftOutput = velocityPercent - turnPercent;
        rightOutput = velocityPercent + turnPercent;

        System.out.println("Left Encoder: " + leftEncoder.getDistance());
        System.out.println("Right Encoder: " + rightEncoder.getDistance());

        leftMaster.set(leftOutput);
        rightMaster.set(rightOutput);
    }

    public void stop() {
        leftMaster.stopMotor();
        rightMaster.stopMotor();
    }

    public void shift(GEAR gear) {
        if(currentGear != gear) {
            shiftGears();
        }
    }

    public void shift() {
        shiftGears();
    }

    private void shiftGears() {
        if(currentGear == GEAR.HIGH) {
            shifter.set(Value.kReverse);
            currentGear = GEAR.LOW;
        }
        else if(currentGear == GEAR.HIGH) {
            shifter.set(Value.kForward);
            currentGear = GEAR.HIGH;
        }
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public enum GEAR {
        HIGH,
        LOW;
    }
    
}
