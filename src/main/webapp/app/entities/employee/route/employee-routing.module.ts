import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EmployeeComponent } from '../list/employee.component';
import { EmployeeDetailComponent } from '../detail/employee-detail.component';
import { EmployeeRoutingResolveService } from './employee-routing-resolve.service';

const employeeRoute: Routes = [
  {
    path: '',
    component: EmployeeComponent,
    data: {
      defaultSort: 'id,asc',
    },
  },
  {
    path: ':id/view',
    component: EmployeeDetailComponent,
    resolve: {
      employee: EmployeeRoutingResolveService,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(employeeRoute)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
