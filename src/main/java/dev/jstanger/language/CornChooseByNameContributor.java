package dev.jstanger.language;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import dev.jstanger.language.psi.CornAssignment;
import dev.jstanger.language.psi.CornPath;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CornChooseByNameContributor implements ChooseByNameContributor {
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        List<CornAssignment> assignments = CornUtil.findAssignments(project, null);
        List<CornPath> paths = CornUtil.findPaths(project);

        List<String> names = new ArrayList<>();

        if(assignments != null) {
            for (CornAssignment assignment : assignments) {
                names.add(assignment.getInputName());
            }
        }

        if(paths != null) {
            for (CornPath path: paths) {
                names.add(path.getName());
            }
        }

        return names.toArray(new String[names.size()]);
    }

    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        List<CornAssignment> assignments = CornUtil.findAssignments(project, name);
        List<CornPath> paths = CornUtil.findPaths(project);

        List<NavigationItem> navigationItems = new ArrayList<>();

        if(assignments != null) {
            for(CornAssignment assignment : assignments) {
                navigationItems.add((NavigationItem) assignment.getInput());
            }
        }

        if(paths != null) {
            for(CornPath path : paths) {
                navigationItems.add((NavigationItem) path);
            }
        }

        return navigationItems.toArray(new NavigationItem[navigationItems.size()]);
    }
}
