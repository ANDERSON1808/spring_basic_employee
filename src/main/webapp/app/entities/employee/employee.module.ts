import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EmployeeComponent } from './list/employee.component';
import { EmployeeDetailComponent } from './detail/employee-detail.component';
import { EmployeeRoutingModule } from './route/employee-routing.module';

@NgModule({
  imports: [SharedModule, EmployeeRoutingModule],
  declarations: [EmployeeComponent, EmployeeDetailComponent],
})
export class EmployeeModule {}
