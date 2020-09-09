package org.lordrose.petclinicwithspring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.service.OwnerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void getFindOwnersForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastName(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownerList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        final Set<Owner> oneOwnerSet = new HashSet<>();
        oneOwnerSet.add(Owner.builder().id(3L).build());
        when(ownerService.findAllByLastName(anyString())).thenReturn(oneOwnerSet);

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/3"));
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownerDetails"))
                .andExpect(model().attribute(
                        "owner", hasProperty("id", is(1L))));

        verify(ownerService).findById(anyLong());
    }

    @Test
    void getCreateForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreateOwnerForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }

    @Test
    void getUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).findById(anyLong());
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }
}
