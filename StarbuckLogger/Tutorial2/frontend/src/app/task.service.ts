import { Injectable } from '@angular/core';
import { WebService } from './web.service';
import Task from './models/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private webService: WebService) {  }

  getLists(){
    return this.webService.get('lists');
  }

  createList(title: string){
    // We want to send a web request to create a list.
    return this.webService.post('lists', {title});
  }

  updateList(id: string, title: string){
    // We want to send a web request to update a list.
    return this.webService.patch(`lists/${id}`, {title});
  }

  updateTask(listId: string, taskId: string, title: string){
    return this.webService.patch(`lists/${listId}/tasks/${taskId}`, {title});
  }

  getTasks(listId: string){
    return this.webService.get(`lists/${listId}/tasks`);
  }

  createTask(listId: string, title: string){
    // We want to send a web request to create a task.
    return this.webService.post(`lists/${listId}/tasks`, {title});
  }

  deleteList(listId: string){
    return this.webService.delete(`lists/${listId}`);
  }

  deleteTask(listId: string, taskId: string){
    return this.webService.delete(`lists/${listId}/tasks/${taskId}`);
  }

  setCompleted(listId: string, task: Task){
    return this.webService.patch(`lists/${listId}/tasks/${task._id}`, {completed: !task.completed});
  }
}
