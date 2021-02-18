import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';

type EntityResponseType = HttpResponse<IGerenciaId>;
type EntityArrayResponseType = HttpResponse<IGerenciaId[]>;

@Injectable({ providedIn: 'root' })
export class GerenciaIdService {
  public resourceUrl = SERVER_API_URL + 'api/gerencia-ids';

  constructor(protected http: HttpClient) {}

  create(gerenciaId: IGerenciaId): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gerenciaId);
    return this.http
      .post<IGerenciaId>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gerenciaId: IGerenciaId): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gerenciaId);
    return this.http
      .put<IGerenciaId>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGerenciaId>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGerenciaId[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gerenciaId: IGerenciaId): IGerenciaId {
    const copy: IGerenciaId = Object.assign({}, gerenciaId, {
      dataGeracao: gerenciaId.dataGeracao && gerenciaId.dataGeracao.isValid() ? gerenciaId.dataGeracao.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataGeracao = res.body.dataGeracao ? moment(res.body.dataGeracao) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gerenciaId: IGerenciaId) => {
        gerenciaId.dataGeracao = gerenciaId.dataGeracao ? moment(gerenciaId.dataGeracao) : undefined;
      });
    }
    return res;
  }
}
