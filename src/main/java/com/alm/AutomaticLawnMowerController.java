package com.alm;

import com.alm.model.AutomaticLawnMower;
import com.alm.model.Lawn;
import com.alm.model.Orientation;
import com.alm.model.Position;
import com.alm.service.AutomaticLawnMowerService;
import com.alm.service.AutomaticLawnMowerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AutomaticLawnMowerController {
    private static final Logger LOG = Logger.getLogger(AutomaticLawnMowerController.class.getName());

    public static void main(String[] args) {
        if (args.length != 1) {
            LOG.info("Please provide a file path");
            System.exit(1);
        }
        String filePath = args[0];
        String instructionsStr;
        Position position;
        Orientation orientation;
        AutomaticLawnMower automaticLawnMower;

        List<AutomaticLawnMower> automaticLawnMowers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            String[] lineParts = lines.get(0).split(" ");
            Lawn lawn = new Lawn(Integer.parseInt(lineParts[0]), Integer.parseInt(lineParts[1]));
            String newLine = lines.get(0).substring(3);
            lines.set(0, newLine);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                lineParts = line.split(" ");
                position = new Position(Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2]));
                orientation = Orientation.valueOf(lineParts[3]);
                instructionsStr = lineParts[4];
                automaticLawnMower = new AutomaticLawnMower(position, orientation, lawn);
                AutomaticLawnMowerService automaticLawnMowerService = new AutomaticLawnMowerServiceImpl();
                automaticLawnMowers.add(automaticLawnMowerService.execute(automaticLawnMower, instructionsStr));

                if (lines.size() == 1 && lineParts.length >= 6) {
                    line = line.substring(line.indexOf(instructionsStr) + instructionsStr.length());
                    lines.add(i + 1, line);
                }
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        System.out.print("(position finale des tondeuses) :");
        for (AutomaticLawnMower mower : automaticLawnMowers) {
            System.out.print(mower.getPosition() + " " + mower.getOrientation());
        }
    }
}