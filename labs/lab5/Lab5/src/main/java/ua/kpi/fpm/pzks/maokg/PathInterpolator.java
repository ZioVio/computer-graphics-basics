package ua.kpi.fpm.pzks.maokg;

import java.security.InvalidAlgorithmParameterException;

public class PathInterpolator {
    public static class Coordinats3D {
        public float currentX = 0,
        currentY = 0,
        currentZ = 0,
        currentScale = 0,
        currentRotateX = 0,
        currentRotateY = 0,
        currentRotateZ = 0;
        public Coordinats3D(float x, float y, float z, float scale, float rotX, float rotY, float rotZ) {
            this.currentX = x;
            this.currentY = y;
            this.currentZ = z;
            this.currentScale = scale;
            this.currentRotateX = rotX;
            this.currentRotateY = rotY;
            this.currentRotateZ = rotZ;
        }
    }

    Coordinats3D[] initialPath;
    Coordinats3D[] interpolatedPath;
    private int iterationsCount;
    private int maxIterationNumber;

    public PathInterpolator(Coordinats3D[] path, int iterationsCount) {
        this.initialPath = path;
        this.iterationsCount = iterationsCount;
        updateInterpolatedPath(path, iterationsCount);
    }

    public int getMaxIterationNumber() {
        return maxIterationNumber;
    }

    public Coordinats3D[] getInterpolatedPath() {
        return this.interpolatedPath;
    }

    private void updateInterpolatedPath(Coordinats3D[] notInterpolatedPath, int iterationsCount) {
        this.maxIterationNumber = iterationsCount - (iterationsCount % (notInterpolatedPath.length - 1));
        System.out.println(maxIterationNumber);
        this.interpolatedPath = new Coordinats3D[this.maxIterationNumber];
        int onePathLength = this.maxIterationNumber / (notInterpolatedPath.length - 1);
        for (int i = 0; i <= maxIterationNumber - onePathLength; i += onePathLength) {
            Coordinats3D currentPathPoint = this.initialPath[i / onePathLength];
            Coordinats3D nextPathPoint = this.initialPath[i / onePathLength + 1];
            float xDiff = (nextPathPoint.currentX - currentPathPoint.currentX) / onePathLength;
            float yDiff = (nextPathPoint.currentY - currentPathPoint.currentY) / onePathLength;
            float zDiff = (nextPathPoint.currentZ - currentPathPoint.currentZ) / onePathLength;
            float scaleDiff = (nextPathPoint.currentScale - currentPathPoint.currentScale) / onePathLength;
            float rotXDiff = (nextPathPoint.currentRotateX - currentPathPoint.currentRotateX) / onePathLength;
            float rotYDiff = (nextPathPoint.currentRotateY - currentPathPoint.currentRotateY) / onePathLength;
            float rotZDiff = (nextPathPoint.currentRotateZ - currentPathPoint.currentRotateZ) / onePathLength;
            for (int j = 0; j < onePathLength; j++) {
                Coordinats3D interpolatedCoords = new Coordinats3D(
                        currentPathPoint.currentX + xDiff * j,
                        currentPathPoint.currentY + yDiff * j,
                        currentPathPoint.currentZ + zDiff * j,
                        currentPathPoint.currentScale + scaleDiff * j,
                        currentPathPoint.currentRotateX + rotXDiff * j,
                        currentPathPoint.currentRotateY + rotYDiff * j,
                        currentPathPoint.currentRotateZ + rotZDiff * j
                );

                this.interpolatedPath[i + j] = interpolatedCoords;
            }
        }
    }
}
