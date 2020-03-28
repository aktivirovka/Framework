package service;


import model.Engine;

public class EngineCreator {

    public static final String TESTDATA_ENGINE_NUMBER_OF_INSTANCES = "testdata.engine.numberOfInstances";
    public static final String TESTDATA_ENGINE_SOFTWARE = "testdata.engine.software";
    public static final String TESTDATA_ENGINE_MACHINE_CLASS = "testdata.engine.machineClass";
    public static final String TESTDATA_ENGINE_MACHINE_TYPE = "testdata.engine.machineType";
    public static final String TESTDATA_ENGINE_NUMBER_OF_GPUS = "testdata.engine.numberOfGPUs";
    public static final String TESTDATA_ENGINE_GPU_TYPE = "testdata.engine.GPUType";
    public static final String TESTDATA_ENGINE_DATA_CENTER_LOCATION = "testdata.engine.dataCenterLocation";
    public static final String TESTDATA_ENGINE_LOCAL_SSD = "testdata.engine.localSSD";
    public static final String TESTDATA_ENGINE_COMMITED_USAGE = "testdata.engine.commitedUsage";
    public static final String TESTDATA_ENGINE_TOTAL_COST = "testdata.engine.totalCost";

    public static Engine withCredentialsFromProperty() {
        return new Engine(TestDataReader.getTestData(TESTDATA_ENGINE_NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_ENGINE_SOFTWARE),
                TestDataReader.getTestData(TESTDATA_ENGINE_MACHINE_CLASS),
                TestDataReader.getTestData(TESTDATA_ENGINE_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_ENGINE_NUMBER_OF_GPUS),
                TestDataReader.getTestData(TESTDATA_ENGINE_GPU_TYPE),
                TestDataReader.getTestData(TESTDATA_ENGINE_DATA_CENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_ENGINE_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_ENGINE_COMMITED_USAGE),
                TestDataReader.getTestData(TESTDATA_ENGINE_TOTAL_COST));
    }
}
