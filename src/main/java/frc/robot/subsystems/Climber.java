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

        state = BRAKE_STATE.DISENGAGED;

        brake = new DoubleSolenoid(constants.CB_BRAKE_F, constants.CB_BRAKE_B);
    }

    public void update(double percentOutput) {
        liftRight.set(percentOutput);
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
    
    enum BRAKE_STATE {
        ENGAGED,
        DISENGAGED;
    }
}
