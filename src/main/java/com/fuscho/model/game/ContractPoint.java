package com.fuscho.model.game;

public enum ContractPoint {
    QUATREVINGTS(80), QUATREVINGTSDIX(90), CENT(100), CENTDIX(110), CENTVINGTS(120), CENTTRENTE(130), CENTQUARANTE(140), CINTCINQUANTE(150), CAPOT(250);
    private final Integer nbPoint;

    ContractPoint(Integer point) {
        this.nbPoint = point;
    }

    public static ContractPoint fromValue(Integer point) {
        if (point != null) {
            for (ContractPoint b : ContractPoint.values()) {
                if (point.equals(b.nbPoint)) {
                    return b;
                }
            }
        }
        return null;
    }

    public Integer getNbPoint(){
        return nbPoint;
    }
}