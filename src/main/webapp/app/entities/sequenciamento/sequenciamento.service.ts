import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISequenciamento } from 'app/shared/model/sequenciamento.model';

type EntityResponseType = HttpResponse<ISequenciamento>;
type EntityArrayResponseType = HttpResponse<ISequenciamento[]>;

@Injectable({ providedIn: 'root' })
export class SequenciamentoService {
  public resourceUrl = SERVER_API_URL + 'api/sequenciamentos';

  constructor(protected http: HttpClient) {}

  create(sequenciamento: ISequenciamento): Observable<EntityResponseType> {
    return this.http.post<ISequenciamento>(this.resourceUrl, sequenciamento, { observe: 'response' });
  }

  update(sequenciamento: ISequenciamento): Observable<EntityResponseType> {
    return this.http.put<ISequenciamento>(this.resourceUrl, sequenciamento, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISequenciamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISequenciamento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
