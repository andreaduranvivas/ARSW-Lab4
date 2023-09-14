package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import edu.eci.arsw.blueprints.persistence.impl.RedundancyFilter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RedundancyFilterTest {
    private BlueprintFilter redundancyFilter = new RedundancyFilter();
    @Test
    public void testFilterDeleteRedundancy() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));

        Point[] newPoints = redundancyFilter.toArrayPoints(points);
        Blueprint blueprint = new Blueprint("Author", "BlueprintName", newPoints);

        Blueprint filteredBlueprint = redundancyFilter.filter(blueprint);

        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(1, 1));
        expectedPoints.add(new Point(2, 2));
        expectedPoints.add(new Point(3, 3));

        Point[] expectedFilteredPoints = redundancyFilter.toArrayPoints(expectedPoints);
        Blueprint expectedBlueprint = new Blueprint("Author", "BlueprintName", expectedFilteredPoints);

        for (int i=0;i<expectedBlueprint.getPoints().size();i++) {
            assertEquals(expectedBlueprint.getPoints().get(i).getX(), filteredBlueprint.getPoints().get(i).getX());
            assertEquals(expectedBlueprint.getPoints().get(i).getY(), filteredBlueprint.getPoints().get(i).getY());
        }
    }


    @Test
    public void testFilterNotDeleteRedundancy() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        Point[] newPoints = redundancyFilter.toArrayPoints(points);
        Blueprint blueprint = new Blueprint("Author", "BlueprintName", newPoints);

        Blueprint filteredBlueprint = redundancyFilter.filter(blueprint);

        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(1, 1));
        expectedPoints.add(new Point(2, 2));
        expectedPoints.add(new Point(3, 3));
        expectedPoints.add(new Point(4, 4));

        Point[] expectedFilteredPoints = redundancyFilter.toArrayPoints(expectedPoints);
        Blueprint expectedBlueprint = new Blueprint("Author", "BlueprintName", expectedFilteredPoints);

        for (int i=0;i<expectedBlueprint.getPoints().size();i++) {
            assertEquals(expectedBlueprint.getPoints().get(i).getX(), filteredBlueprint.getPoints().get(i).getX());
            assertEquals(expectedBlueprint.getPoints().get(i).getY(), filteredBlueprint.getPoints().get(i).getY());
        }
    }
}
