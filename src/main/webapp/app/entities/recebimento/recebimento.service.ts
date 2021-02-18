import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecebimento } from 'app/shared/model/recebimento.model';

type EntityResponseType = HttpResponse<IRecebimento>;
type EntityArrayResponseType = HttpResponse<IRecebimento[]>;

@Injectable({ providedIn: 'root' })
export class RecebimentoService {
  public resourceUrl = SERVER_API_URL + 'api/recebimentos';

  constructor(protected http: HttpClient) {}

  create(recebimento: IRecebimento): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recebimento);
    return this.http
      .post<IRecebimento>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recebimento: IRecebimento): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recebimento);
    return this.http
      .put<IRecebimento>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecebimento>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecebimento[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(recebimento: IRecebimento): IRecebimento {
    const copy: IRecebimento = Object.assign({}, recebimento, {
      data: recebimento.data && recebimento.data.isValid() ? recebimento.data.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((recebimento: IRecebimento) => {
        recebimento.data = recebimento.data ? moment(recebimento.data) : undefined;
      });
    }
    return res;
  }
}
