package kitchenpos.bo;

import kitchenpos.dao.MenuGroupDao;
import kitchenpos.model.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Geonguk Han
 * @since 2020-02-15
 */
@ExtendWith(MockitoExtension.class)
class MenuGroupBoTest {

    @Mock
    private MenuGroupDao menuGroupDao;

    @InjectMocks
    private MenuGroupBo menuGroupBo;


    @Test
    @DisplayName("메뉴 그룹을 등록 할 수 있다.")
    void create() {
        final MenuGroup menuGroup = getMenuGroup();

        given(menuGroupDao.save(menuGroup)).willReturn(menuGroup);

        final MenuGroup savedMenuGroup = menuGroupBo.create(menuGroup);

        assertThat(savedMenuGroup).isNotNull();
        assertThat(savedMenuGroup.getId()).isEqualTo(menuGroup.getId());

    }

    private MenuGroup getMenuGroup() {
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(1l);
        menuGroup.setName("면 요리 세트");
        return menuGroup;
    }

    private MenuGroup getMenuGroup2() {
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(2l);
        menuGroup.setName("밥 요리 세트");
        return menuGroup;
    }

    @Test
    @DisplayName("메뉴 그룹 목록을 조회 할 수 있다.")
    void list() {

        final List<MenuGroup> menuGroupList = Arrays.asList(getMenuGroup(), getMenuGroup2());
        given(menuGroupDao.findAll()).willReturn(menuGroupList);

        final List<MenuGroup> result = menuGroupBo.list();

        assertThat(menuGroupList).isNotEmpty();
        assertThat(menuGroupList.size()).isEqualTo(result.size());

    }

}