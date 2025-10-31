import java.util.Random;
enum TaskType { LOAD, UNLOAD, TRANSFER, CHARGE }
enum TaskStatus { PENDING, IN_PROGRESS, COMPLETED, FAILED }
abstract class Task {
        protected final String taskId;
        protected final TaskType type;
        protected TaskStatus status;
        protected final String parcelId;

        public Task(String taskId, TaskType type, String parcelId) {
            this.taskId = taskId;
            this.type = type;
            this.parcelId = parcelId;
            this.status = TaskStatus.PENDING;
        }

        public String getTaskId() { return taskId; }
        public TaskType getType() { return type; }
        public TaskStatus getStatus() { return status; }
        public String getParcelId() { return parcelId; }

        public abstract void execute(int robot);

        public void markComplete() { this.status = TaskStatus.COMPLETED; }
        public void markInProgress() { this.status = TaskStatus.IN_PROGRESS; }
        public void markFailed() { this.status = TaskStatus.FAILED; }
}
    //Docking loading task
     class LoadTask extends Task {
        private final int loadingDock;

        public LoadTask(String taskId, String parcelId, int dock) {
            super(taskId, TaskType.LOAD, parcelId);
            this.loadingDock = dock;
        }

        @Override
        public void execute(int robot) {
            markInProgress();
            //write log here of execution
            markComplete();
        }
    }
    //Docking UnlaodTask
    class UnloadTask extends Task {
        private final int unloadingDock;

        public UnloadTask(String taskId, String parcelId, int dock) {
            super(taskId, TaskType.UNLOAD, parcelId);
            this.unloadingDock = dock;
        }

        @Override
        public void execute(int robot) {
            markInProgress();
            Parcel p = new Parcel(parcelId, 1.0, randomDestination(), 1);
            //write log here with parcel unloaded
        }

        private String randomDestination() {
            String[] destinations = {"France", "Germany", "Belgium", "Austria", "Denmark"};
            return destinations[new Random().nextInt(destinations.length)];
        }
    }
    class ChargeTask extends Task {
        public ChargeTask(String taskId, String parcelId) { super(taskId, TaskType.CHARGE, parcelId); }
        @Override
        public void execute(int robot) {
            markInProgress();
            // put logs here for charging
            // find free charging station
        }
    }
