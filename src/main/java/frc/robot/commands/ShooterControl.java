package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends CommandBase {
    
    Shooter shooter;
    XboxController controller;

    public ShooterControl(Shooter shooter, XboxController controller) {
        this.shooter = shooter;
        this.controller = controller;
    }

    @Override
    public void initialize() {
        shooter.update(0, 0);
    }

    @Override
    public void execute() {
        if(controller.getAButton()) shooter.update(3000, 3000);
        else if(controller.getBButton()) shooter.update(4000, 4000);
        else if(controller.getXButton()) shooter.update(5000, 5000);
        else if(controller.getYButton()) shooter.update(6000, 6000);
        else shooter.update(0, 0);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.update(0, 0);
    }
}
