package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.core.domain.model.Character
import com.example.core.repository.CharactersRepository
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository,
): PagingUseCase<GetCharactersUseCase.GetCharactersParams, Character>(), GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersUseCase.GetCharactersParams): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow
    }
}