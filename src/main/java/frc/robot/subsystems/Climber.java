package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    Constants constants = new Constants();

    private final double min = 4;

    boolean raised = false;
    
    CANSparkMax liftRight;
    CANSparkMax liftLeft;
    TalonSRX translator;

    DoubleSolenoid brake;

    private BRAKE_STATE state;

    public Climber() {
        liftRight = new CANSparkMax(constants.CB_RAISE_ONE_ID, MotorType.kBrushless);
        liftLeft = new CANSparkMax(constants.CB_RAISE_TWO_ID, MotorType.kBrushless);
        translator = new TalonSRX(constants.CB_TRANSLATOR_TALON_ID);

        liftRight.getEncoder().setPosition(0);
        liftLeft.getEncoder().setPosition(0);

        liftLeft.follow(liftRight, true);

        liftRight.setInverted(true);
        liftLeft.setInverted(true);

        state = BRAKE_STATE.DISENGAGED;

        brake = new DoubleSolenoid(constants.CB_BRAKE_F, constants.CB_BRAKE_B);
    }

    public void update(double percentOutput) {
        if ((!raised && percentOutput < 0) || (raised && percentOutput < 0 &&
         liftRight.getEncoder().getPosition() < min)) {
            System.out.println("Err: Driving to negative position, aborting");
            return;
        } 
        if(state != BRAKE_STATE.ENGAGED) liftRight.set(percentOutput);

        if(liftRight.getEncoder().getPosition() > min) raised = true;
    }

    public void engageBrake() {
        if(state != BRAKE_STATE.ENGAGED) {
            brake.set(Value.kForward);
            state = BRAKE_STATE.ENGAGED;
        }
    }

    public void disengageBrake() {
        if(state != BRAKE_STATE.DISENGAGED) {
            brake.set(Value.kReverse);
            state = BRAKE_STATE.DISENGAGED;
        }
    }

    public TalonSRX pigeonTalon() {
        return translator;
    }

    public void resetEncoders() {
        liftRight.getEncoder().setPosition(0);
        liftLeft.getEncoder().setPosition(0);
    }

    @Override
    public void periodic() {
        System.out.println("Enc Position: " + liftRight.getEncoder().getPosition());
    }
    
    enum BRAKE_STATE {
        ENGAGED,
        DISENGAGED;
    }
}
