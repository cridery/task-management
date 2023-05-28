import { computed } from 'vue';
import { createStore } from 'vuex';

// define your state
const state = {
    tasks: []
};

// define your mutations
const mutations = {
    setTasks(state, tasks) {
        state.tasks = tasks;
    },
    addTask(state, task) {
        state.tasks.push(task);
    },
    updateTask(state, task) {
        const index = state.tasks.findIndex(t => t.id === task.id);
        if (index !== -1) {
            state.tasks[index] = task;
        }
    }
    ,
    deleteTask(state, taskId) {
        state.tasks = state.tasks.filter(task => task.id !== taskId);
    },

};

// define your actions
const actions = {
    async fetchTasks({ commit }) {
        try {
            const response = await fetch('http://localhost:8080/api/tasks');
            if (!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`);
            }
            const tasks = await response.json();
            commit('setTasks', tasks);
        } catch (error) {
            console.error(error);
        }
    },
    async addTask({ commit }, task) {
        try {
            const response = await fetch('http://localhost:8080/api/tasks', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(task)
            });

            if (!response.ok) {
                const responseData = await response.json();
                throw new Error(`${response.status} ${response.statusText}: ${responseData.message || ''}`);
            }
            commit('addTask', task);
        } catch (error) {
            console.error(error);
        }
    },
    
    async updateTask({ commit }, task) {
        try {
            const response = await fetch(`http://localhost:8080/api/tasks/${task.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(task)
            });
            if (!response.ok) {
                const responseData = await response.json();
                throw new Error(`${response.status} ${response.statusText}: ${responseData.message || ''}`);
            }
        } catch (error) {
            console.error(error);
        }
    },

    async deleteTask({ commit }, taskId) {
        try {
            const response = await fetch(`http://localhost:8080/api/tasks/${taskId}`, {
                method: 'DELETE',
                });
        
        if (!response.ok) {
            const responseData = await response.json();
            throw new Error(`${response.status} ${response.statusText}: ${responseData.message || ''}`);
        }
        commit('deleteTask', taskId);
    } catch(error) {
        console.error(error);
        }
    }

};

export const store = createStore({
    state,
    mutations,
    actions
});


export function useStore() {
    return {
        tasks: computed(() => state.tasks),
    }
}