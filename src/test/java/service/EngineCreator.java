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

    /*private String numberOfInstances = "4";
    private String software = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
    private String machineClass = "Regular";
    private String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private String numberOfGPUs = "1";
    private String GPUType = "NVIDIA Tesla V100";
    private String dataCenterLocation = "Frankfurt (europe-west3)";
    private String localSSD = "2x375 GB";
    private String commitedUsage = "1 Year";
    private String totalCost = "1,082.77";*/

    public static Engine withCredentialsFromProperty(){
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
