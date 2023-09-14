package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import edu.eci.arsw.blueprints.persistence.impl.SubsamplingFilter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SubsamplingFilterTest {

    private BlueprintFilter subSamplingFilter = new SubsamplingFilter();
    @Test
    public void testFilterSubsampling() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        Point[] newPoints = subSamplingFilter.toArrayPoints(points);
        Blueprint blueprint = new Blueprint("Author", "BlueprintName", newPoints);

        Blueprint filteredBlueprint = subSamplingFilter.filter(blueprint);

        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(1, 1));
        expectedPoints.add(new Point(3, 3));

        Point[] expectedFilteredPoints = subSamplingFilter.toArrayPoints(expectedPoints);
        Blueprint expectedBlueprint = new Blueprint("Author", "BlueprintName", expectedFilteredPoints);

        for (int i=0;i<expectedBlueprint.getPoints().size();i++) {
            assertEquals(expectedBlueprint.getPoints().get(i).getX(), filteredBlueprint.getPoints().get(i).getX());
            assertEquals(expectedBlueprint.getPoints().get(i).getY(), filteredBlueprint.getPoints().get(i).getY());
        }
    }
}
