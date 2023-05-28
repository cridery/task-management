<template>
    <div class="w-full flex space-x-4  border-gray-200 ">
        <div class="w-1/4 px-4 py-4 border-r-2 border-gray-200 h-screen bg-gray-50">
            <div v-for="task in tasks" :key="task.id" class="hover:bg-gray-100 rounded-lg p-2.5" @click="selectTask(task)">
                <div class="flex justify-between items-center">
                    <h3 class="cursor-pointer text-lg font-medium">{{ task.title }}</h3>
                    <div class="space-x-2">
                        <button @click.stop="enterEditMode(task.id)">
                            <EditIcon/>
                        </button>
                        <button @click.stop="deleteTask(task.id)">
                           <DeleteIcon/>
                        </button>
                    </div>
                </div>
                <p class="cursor-pointer text-gray-400">{{ shortenDescription(task.description) }}</p>
            </div>
            <button @click="showTaskForm = true" class="flex items-center py-2.5 px-5 mr-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700 my-2">
                <PlusIcon/> Add new task
            </button>
        </div>
        <div class="w-4/5 grid justify-center space-y-3 pt-4">
            <template v-if="selectedTask && !showTaskForm && !editModeTaskId">
                <TaskDetail :task="selectedTask" />
            </template>
            <template v-else-if="editModeTaskId">
                <TaskEdit :task="selectedTask" @task-updated="updateTask" @cancel-edit="exitEditMode" />
            </template>
            <template v-else-if="showTaskForm">
                <TaskForm @task-added="fetchTasks" @task-cancelled="showTaskForm = false" />
            </template>
        </div>
    </div>
</template>

<script>
import TaskForm from "./TaskForm.vue";
import TaskDetail from "./TaskDetail.vue";
import TaskEdit from "./TaskEdit.vue";

import EditIcon from "./Icons/EditIcon.vue";
import DeleteIcon from "./Icons/DeleteIcon.vue"
import PlusIcon from "./Icons/PlusIcon.vue"

import { ref, onMounted } from "vue";
import { store } from "../store";


export default {
    components: {
        TaskDetail,
        TaskForm,
        TaskEdit,
        EditIcon,
        DeleteIcon,
        PlusIcon
    },
    name: "TaskList",
    setup() {
        const tasks = ref([]);
        const selectedTask = ref(null);
        const showTaskForm = ref(false);
        const editModeTaskId = ref(null);

        const selectTask = (task) => {
            selectedTask.value = task;
            showTaskForm.value = false; // Hide the task form when a task is selected
            exitEditMode();
        };

        const enterEditMode = (taskId) => {
            const task = tasks.value.find((t) => t.id === taskId);
            if (task) {
                selectedTask.value = task;
            }
                editModeTaskId.value = taskId;

        };

        const exitEditMode = () => {
            editModeTaskId.value = null;
        };

        const shortenDescription = (desc) => {
            return desc.length > 50 ? desc.substring(0, 47) + "..." : desc;
        };

        const fetchTasks = async () => {
            try {
                await store.dispatch("fetchTasks");
                tasks.value = store.state.tasks;
            } catch (error) {
                console.error(error);
            }
        };

        const updateTask = async (task) => {
            try {
                await store.dispatch("updateTask", task);
                selectedTask.value = null;
                exitEditMode();
                fetchTasks();
            } catch (error) {
                console.error(error);
            }
        };

        const deleteTask = async (taskId) => {
            try {
                await store.dispatch("deleteTask", taskId);
                selectedTask.value = null;
                exitEditMode();
                fetchTasks();
            } catch (error) {
                console.error(error);
            }
        };

        onMounted(fetchTasks);

        return {
            tasks,
            selectedTask,
            showTaskForm,
            editModeTaskId,
            selectTask,
            enterEditMode,
            exitEditMode,
            shortenDescription,
            deleteTask,
            updateTask,
        };
    },
};
</script>
