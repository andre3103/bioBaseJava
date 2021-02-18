import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGerenciaId, GerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from './gerencia-id.service';
import { GerenciaIdComponent } from './gerencia-id.component';
import { GerenciaIdDetailComponent } from './gerencia-id-detail.component';
import { GerenciaIdUpdateComponent } from './gerencia-id-update.component';

@Injectable({ providedIn: 'root' })
export class GerenciaIdResolve implements Resolve<IGerenciaId> {
  constructor(private service: GerenciaIdService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGerenciaId> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gerenciaId: HttpResponse<GerenciaId>) => {
          if (gerenciaId.body) {
            return of(gerenciaId.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GerenciaId());
  }
}

export const gerenciaIdRoute: Routes = [
  {
    path: '',
    component: GerenciaIdComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.gerenciaId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GerenciaIdDetailComponent,
    resolve: {
      gerenciaId: GerenciaIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.gerenciaId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GerenciaIdUpdateComponent,
    resolve: {
      gerenciaId: GerenciaIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.gerenciaId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GerenciaIdUpdateComponent,
    resolve: {
      gerenciaId: GerenciaIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.gerenciaId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
