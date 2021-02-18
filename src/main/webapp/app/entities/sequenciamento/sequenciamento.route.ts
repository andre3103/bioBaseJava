import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISequenciamento, Sequenciamento } from 'app/shared/model/sequenciamento.model';
import { SequenciamentoService } from './sequenciamento.service';
import { SequenciamentoComponent } from './sequenciamento.component';
import { SequenciamentoDetailComponent } from './sequenciamento-detail.component';
import { SequenciamentoUpdateComponent } from './sequenciamento-update.component';

@Injectable({ providedIn: 'root' })
export class SequenciamentoResolve implements Resolve<ISequenciamento> {
  constructor(private service: SequenciamentoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISequenciamento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sequenciamento: HttpResponse<Sequenciamento>) => {
          if (sequenciamento.body) {
            return of(sequenciamento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Sequenciamento());
  }
}

export const sequenciamentoRoute: Routes = [
  {
    path: '',
    component: SequenciamentoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.sequenciamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SequenciamentoDetailComponent,
    resolve: {
      sequenciamento: SequenciamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.sequenciamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SequenciamentoUpdateComponent,
    resolve: {
      sequenciamento: SequenciamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.sequenciamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SequenciamentoUpdateComponent,
    resolve: {
      sequenciamento: SequenciamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.sequenciamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
