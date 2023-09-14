package edu.eci.arsw.blueprints.ui;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws BlueprintNotFoundException, BlueprintPersistenceException {

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bp = ac.getBean(BlueprintsServices.class);

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10),new Point(88, 4)};
        Blueprint bp1=new Blueprint("john", "thepaint",pts);
        Blueprint bp2=new Blueprint("camilo", "theartist",pts);
        Blueprint bp3=new Blueprint("andrea", "omg",pts);

        Point[] pts1=new Point[]{new Point(0, 0),new Point(0, 0),new Point(0, 0)};
        Blueprint bp4=new Blueprint("freya", "mycat",pts1);

        Point[] pts2=new Point[]{new Point(0, 0),new Point(1, 1) ,new Point(0, 0)};
        Blueprint bp5=new Blueprint("maxi", "mydog",pts2);

        Point[] pts3=new Point[]{new Point(2, 2),new Point(0, 0) ,new Point(0, 0)};
        Blueprint bp6=new Blueprint("marga", "mymom",pts3);

        // Register plans
        bp.addNewBlueprint(bp1);
        bp.addNewBlueprint(bp2);
        bp.addNewBlueprint(bp3);
        bp.addNewBlueprint(bp4);
        bp.addNewBlueprint(bp5);
        bp.addNewBlueprint(bp6);


        // Consult plans
        System.out.println(bp.getAllBlueprints());

        // Consult specific plans
        System.out.println("Searching specific blueprint: " + bp.getBlueprint(bp1.getAuthor(), bp1.getName()));
        System.out.println("Searching blueprint by author: " + bp.getBlueprintsByAuthor(bp2.getAuthor()));

        //Filter A : Redundancy and Filter B : Subsampling
        System.out.println("Filtering results: ");
        Set<Blueprint> newBps = bp.applyFilterForAll();

        for(Blueprint fbp : newBps){
            System.out.println(fbp);
            for (int i=0;i<fbp.getPoints().size();i++) {
                System.out.println("X = " + fbp.getPoints().get(i).getX() + " Y = " + fbp.getPoints().get(i).getY());
            }
        }
    }
}
