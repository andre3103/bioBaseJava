import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEntrega, Entrega } from 'app/shared/model/entrega.model';
import { EntregaService } from './entrega.service';
import { EntregaComponent } from './entrega.component';
import { EntregaDetailComponent } from './entrega-detail.component';
import { EntregaUpdateComponent } from './entrega-update.component';

@Injectable({ providedIn: 'root' })
export class EntregaResolve implements Resolve<IEntrega> {
  constructor(private service: EntregaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntrega> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((entrega: HttpResponse<Entrega>) => {
          if (entrega.body) {
            return of(entrega.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Entrega());
  }
}

export const entregaRoute: Routes = [
  {
    path: '',
    component: EntregaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.entrega.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EntregaDetailComponent,
    resolve: {
      entrega: EntregaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.entrega.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EntregaUpdateComponent,
    resolve: {
      entrega: EntregaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.entrega.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EntregaUpdateComponent,
    resolve: {
      entrega: EntregaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.entrega.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
