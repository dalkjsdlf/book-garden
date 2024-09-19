package io.ratel.bookgarden.userbook.controller;

import io.ratel.bookgarden.common.aop.ApiControllerAdvice;
import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import io.ratel.bookgarden.web_api.userbook.application.UserBookApplication;
import io.ratel.bookgarden.web_api.userbook.controller.UserBookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserBookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserBookController userBookController;

    @MockBean
    private UserBookApplication  userBookApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userBookController).
                setControllerAdvice(new ApiControllerAdvice()).build();
    }

    @Test
    @DisplayName("Get all user books - Success")
    void testGetAllUserBooks() throws Exception {
        Long userId = 1L;
        String title = "Book Title";
        Yn yn = Yn.Y;
        mockMvc.perform(get("/api/v1/userbooks").
                        header(WebApiConst.USER_ID_HEADER, userId).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].title").value(title)).
                andExpect(jsonPath("$[0].readCmpYn").value(yn));
    }
/*
    @Test
    @DisplayName("Get user book by ID - Success")
    void testGetUserBookById() throws Exception {
        UserBookGetResponseDto responseDto = new UserBookGetResponseDto("Book Title", "Cover Image URL", UserBookEntity.ReadCmpYn.Y);
        when(userBookService.getUserBookById(anyLong())).thenReturn(Optional.of(responseDto));

        mockMvc.perform(get("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Book Title"))
                .andExpect(jsonPath("$.cover").value("Cover Image URL"))
                .andExpect(jsonPath("$.readCmpYn").value("Y"));
    }

    @Test
    @DisplayName("Get user book by ID - Not Found")
    void testGetUserBookByIdNotFound() throws Exception {
        when(userBookService.getUserBookById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create user book - Success")
    void testCreateUserBook() throws Exception {
        UserBookCreateRequestDto requestDto = new UserBookCreateRequestDto();
        requestDto.setBookId(1L);
        requestDto.setReadCmpYn(UserBookEntity.ReadCmpYn.N);

        when(userBookService.createUserBook(any(UserBookCreateRequestDto.class)))
                .thenReturn(new UserBookEntity(1L, "test-user-id", 1L, UserBookEntity.ReadCmpYn.N));

        mockMvc.perform(post("/api/v1/userbooks")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create user book - Bad Request")
    void testCreateUserBookBadRequest() throws Exception {
        UserBookCreateRequestDto requestDto = new UserBookCreateRequestDto();
        requestDto.setBookId(null); // Invalid request: bookId is required

        mockMvc.perform(post("/api/v1/userbooks")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update user book - Success")
    void testUpdateUserBook() throws Exception {
        UserBookUpdateRequestDto updateRequestDto = new UserBookUpdateRequestDto();
        updateRequestDto.setReadCmpYn(UserBookEntity.ReadCmpYn.Y);

        when(userBookService.updateUserBook(anyLong(), any(UserBookUpdateRequestDto.class)))
                .thenReturn(Optional.of(new UserBookEntity(1L, "test-user-id", 1L, UserBookEntity.ReadCmpYn.Y)));

        mockMvc.perform(put("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update user book - Not Found")
    void testUpdateUserBookNotFound() throws Exception {
        UserBookUpdateRequestDto updateRequestDto = new UserBookUpdateRequestDto();
        updateRequestDto.setReadCmpYn(UserBookEntity.ReadCmpYn.Y);

        when(userBookService.updateUserBook(anyLong(), any(UserBookUpdateRequestDto.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete user book - Success")
    void testDeleteUserBook() throws Exception {
        mockMvc.perform(delete("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userBookService, times(1)).deleteUserBook(anyLong());
    }

    @Test
    @DisplayName("Delete user book - Not Found")
    void testDeleteUserBookNotFound() throws Exception {
        doThrow(new RuntimeException("Book not found")).when(userBookService).deleteUserBook(anyLong());

        mockMvc.perform(delete("/api/v1/userbooks/1")
                        .header("X_USER_ID", "test-user-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

 */
}
