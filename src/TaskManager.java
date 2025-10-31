import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
class TaskManager  {
        private final Queue<Task> tasks = new ArrayDeque<>();
        private final LogManager logManager;
        private static int task_counter=0;

        public TaskManager() {
            this.logManager = new LogManager();
            logManager.CreateLogFile("TASK");
        }
        //docking createLoadTask
        public Task createUnLoadTask(int dock) {
            String tid = "T-" + task_counter;
            task_counter++;
            String pid = "FR-102-2";
            Task t = new UnloadTask(tid, pid, dock);
            tasks.add(t);
            logManager.writeLog("Created task " + tid + " UNLOAD " + pid + " for " + dock);
            return t;
        }

        //docking createUnloadTask
        public Task createloadTask(String parcelId, int dock) {
            String tid = "T-" + task_counter;
            task_counter++;
            Task t = new LoadTask(tid, parcelId, dock);
            tasks.add(t);
            logManager.writeLog("Created task " + tid + " LOAD " + parcelId + " at " + dock);
            return t;
        }
        public void assignTask(int robot, Task task) {
            logManager.writeLog("Assigned task " + task.getTaskId() + " to robot " + robot);
            task.execute(robot); 
            logManager.writeLog("Executed Task "+task.taskId);
        }
        public void processPendingTasks() {
            // simple assignment policy: assign tasks to nearest idle robot
            if (tasks.isEmpty() /*|| i <=5*/) {
            	//i++;
            	return;
            }
            Iterator<Task> it = tasks.iterator();
                while (it.hasNext()) {
                    Task t = it.next();
                    // We have to find next available Robot here
                    //For now we are just using a single robot
                     int r = 1;   
                    assignTask(r, t);
                    it.remove();
                }
        }

        public List<Task> getQueuedTasks() {
            return new ArrayList<>(tasks);
        }
    }