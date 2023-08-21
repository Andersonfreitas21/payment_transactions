package com.picpaysimplificado.util;

import com.picpaysimplificado.domain.user.dto.request.UserRequestDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.dto.response.UserResponseDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@NoArgsConstructor
public class Util {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static User convertToEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

    public static UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


    public static User mapSourceToEntity(UserRequestDTO source, User destination) {
        destination.setFirstName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setDocument(source.getDocument());
        destination.setEmail(source.getEmail());
        destination.setBalance(source.getBalance());
        destination.setPassword(source.getPassword());
        destination.setUserType(source.getUserType());
        return destination;
    }
}