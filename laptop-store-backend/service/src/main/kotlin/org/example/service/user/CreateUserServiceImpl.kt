package org.example.service.user

import org.apache.commons.lang3.StringUtils
import org.example.model.input.CreateUserInput
import org.springframework.stereotype.Service

@Service
class CreateUserServiceImpl : CreateUserService {
    override fun execute(createUserInput: CreateUserInput) {
        println(StringUtils.normalizeSpace(createUserInput.name))
    }
}
