package model;

import java.util.Objects;

public class Engine {

    private String numberOfInstances;
    private String software;
    private String machineClass;
    private String machineType;
    private String numberOfGPUs;
    private String GPUType;
    private String dataCenterLocation;
    private String localSSD;
    private String commitedUsage;
    private String totalCost;

    public Engine(String numberOfInstances, String software, String machineClass,
                  String machineType, String numberOfGPUs, String GPUType, String dataCenterLocation,
                  String localSSD, String commitedUsage, String totalCost) {
        this.numberOfInstances = numberOfInstances;
        this.software = software;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.numberOfGPUs = numberOfGPUs;
        this.GPUType = GPUType;
        this.dataCenterLocation = dataCenterLocation;
        this.localSSD = localSSD;
        this.commitedUsage = commitedUsage;
        this.totalCost = totalCost;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public void setMachineClass(String machineClass) {
        this.machineClass = machineClass;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    public String getGPUType() {
        return GPUType;
    }

    public void setGPUType(String GPUType) {
        this.GPUType = GPUType;
    }

    public String getDataCenterLocation() {
        return dataCenterLocation;
    }

    public void setDataCenterLocation(String dataCenterLocation) {
        this.dataCenterLocation = dataCenterLocation;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getCommitedUsage() {
        return commitedUsage;
    }

    public void setCommitedUsage(String commitedUsage) {
        this.commitedUsage = commitedUsage;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return numberOfInstances.equals(engine.numberOfInstances) &&
                software.equals(engine.software) &&
                machineClass.equals(engine.machineClass) &&
                machineType.equals(engine.machineType) &&
                numberOfGPUs.equals(engine.numberOfGPUs) &&
                GPUType.equals(engine.GPUType) &&
                dataCenterLocation.equals(engine.dataCenterLocation) &&
                localSSD.equals(engine.localSSD) &&
                commitedUsage.equals(engine.commitedUsage) &&
                totalCost.equals(engine.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, software, machineClass, machineType, numberOfGPUs, GPUType, dataCenterLocation, localSSD, commitedUsage, totalCost);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", software='" + software + '\'' +
                ", machineClass='" + machineClass + '\'' +
                ", machineType='" + machineType + '\'' +
                ", numberOfGPUs='" + numberOfGPUs + '\'' +
                ", GPUType='" + GPUType + '\'' +
                ", dataCenterLocation='" + dataCenterLocation + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", commitedUsage='" + commitedUsage + '\'' +
                ", totalCost='" + totalCost + '\'' +
                '}';
    }
}
